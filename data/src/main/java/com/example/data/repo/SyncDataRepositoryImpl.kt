package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.response.ChampDBO
import com.example.data.local.response.ItemsDBO
import com.example.data.local.service.*
import com.example.data.mapper.syncDataMappers.*
import com.example.data.remote.SyncDataApiService
import com.example.data.reponse.ChampionResponse
import com.example.data.reponse.ItemResponse
import com.example.domain.entities.ChampsEntity
import com.example.domain.repo.SyncDataRepository

class SyncDataRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val syncDataApiService: SyncDataApiService,
    private val champDAO: ChampDAO,
    private val champTraitsDAO: ChampTraitsDAO,
    private val champItemsDAO: ChampItemsDAO,
    private val champTraitsMapper: ChampTraitsMapper,
    private val champsRemoteDBOMapper: ChampsRemoteDBOMapper,
    private val champItemsMapper: ChampItemsMapper,
    private val itemsRemoteDBOMapper: ItemsRemoteDBOMapper,
    private val champsDBOEntityMapper: ChampsDBOEntityMapper,
    private val itemsDAO: ItemsDAO,
    private val setDAO: SetDAO,
    private val traitsDAO: TraitsDAO,
    private val traitRemoteToDBOMapper: TraitRemoteToDBOMapper,
    private val setRemoteToDBOMapper: SetRemoteToDBOMapper
) : SyncDataRepository {

    override suspend fun syncListChamps(): Either<Failure, List<ChampsEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            var champDBOs: List<ChampDBO> = champDAO.getAllChamp()
            if (champDBOs.isNullOrEmpty()) {
                val champsListResponse: List<ChampionResponse> = syncDataApiService.getChampsList()
                val userDBOS: List<ChampDBO> = champsRemoteDBOMapper.mapList(champsListResponse)
                champDBOs = userDBOS
                champDAO.insertChamps(userDBOS)
            }
            if (itemsDAO.getAllItems().isNullOrEmpty()) {
                val result: List<ItemResponse> = syncDataApiService.getItemsList()
                val itemsDBOs: List<ItemsDBO> = itemsRemoteDBOMapper.mapList(result)
                itemsDAO.insertAllItems(itemsDBOs)
            }
            if (champItemsDAO.getChampItems().isNullOrEmpty()) {
                val champsListResponse: List<ChampionResponse> = syncDataApiService.getChampsList()
                val champsTraitsDBO = champItemsMapper.mapList(champsListResponse)
                champsTraitsDBO.forEach { champItemsDAO.insertChampsItems(it) }
            }
            if (champTraitsDAO.getChampTraits().isNullOrEmpty()) {
                val champsListResponse: List<ChampionResponse> = syncDataApiService.getChampsList()
                val champsTraitsDBO = champTraitsMapper.mapList(champsListResponse)
                champsTraitsDBO.forEach { champTraitsDAO.insertChampsTraits(it) }
            }
            if (traitsDAO.getAllTraits().isNullOrEmpty()) {
                val traitResponse = syncDataApiService.getTraitsList()
                val traitDBOs = traitRemoteToDBOMapper.mapList(traitResponse)
                traitsDAO.insertTraits(traitDBOs)

                traitResponse.forEach {
                    val sets = setRemoteToDBOMapper.mapList(it.key, it.sets.defaultEmpty())
                    setDAO.insertSets(sets)
                }
            }
            val list: List<ChampsEntity> = champsDBOEntityMapper.mapList(champDBOs)
            return@runSuspendWithCatchError Either.Success(list)
        }
}