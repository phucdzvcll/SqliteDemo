package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.service.ChampDAO
import com.example.data.local.service.ChampItemsDAO
import com.example.data.local.service.ChampTraitsDAO
import com.example.data.mapper.syncDataMappers.ChampItemsMapper
import com.example.data.mapper.syncDataMappers.ChampTraitsMapper
import com.example.data.remote.SyncDataApiService
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.SyncChampsItemsEntity
import com.example.domain.entities.SyncChampsTrainsEntity
import com.example.domain.repo.SyncDataRepository

class SyncDataRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val syncDataApiService: SyncDataApiService,
    private val champDAO: ChampDAO,
    private val champTraitsDAO: ChampTraitsDAO,
    private val champItemsDAO: ChampItemsDAO,
    private val champTraitsMapper: ChampTraitsMapper,
    private val champItemsMapper: ChampItemsMapper,
) : SyncDataRepository {
    override suspend fun syncChampsTraits(): Either<Failure, SyncChampsTrainsEntity> =
        Either.runSuspendWithCatchError(listOf(remoteExceptionInterceptor)) {
            if (champDAO.getAllChamp().isNullOrEmpty()) {
                val champsListResponse: List<ChampionResponse> = syncDataApiService.getChampsList()
                val champsTraitsDBO = champTraitsMapper.mapList(champsListResponse)
                champsTraitsDBO.forEach { champTraitsDAO.insertChampsTraits(it) }
                return@runSuspendWithCatchError Either.Success(SyncChampsTrainsEntity(syncStatus = SyncChampsTrainsEntity.SyncStatus.Success))
            }
            return@runSuspendWithCatchError Either.Success(SyncChampsTrainsEntity(syncStatus = SyncChampsTrainsEntity.SyncStatus.Success))
        }

    override suspend fun syncChampsItems(): Either<Failure, SyncChampsItemsEntity> =
        Either.runSuspendWithCatchError(listOf(remoteExceptionInterceptor)) {
            if (champDAO.getAllChamp().isNullOrEmpty()) {
                val champsListResponse: List<ChampionResponse> = syncDataApiService.getChampsList()
                val champsTraitsDBO = champItemsMapper.mapList(champsListResponse)
                champsTraitsDBO.forEach { champItemsDAO.insertChampsItems(it) }
                return@runSuspendWithCatchError Either.Success(SyncChampsItemsEntity(syncStatus = SyncChampsItemsEntity.SyncStatus.Success))
            }
            return@runSuspendWithCatchError Either.Success(SyncChampsItemsEntity(syncStatus = SyncChampsItemsEntity.SyncStatus.Success))
        }
}