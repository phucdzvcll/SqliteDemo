package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.response.SetDBO
import com.example.data.local.service.*
import com.example.data.mapper.detail.DetailChampHeaderMapper
import com.example.data.mapper.detail.TraitDBOtoEntityMapper
import com.example.data.mapper.syncDataMappers.*
import com.example.data.remote.SyncDataApiService
import com.example.domain.entities.ChampDetailEntity
import com.example.domain.repo.DetailChampRepository

class DetailChampRepositoryImpl(
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
    private val detailChampHeaderMapper: DetailChampHeaderMapper,
    private val itemsDAO: ItemsDAO,
    private val setDAO: SetDAO,
    private val traitsDAO: TraitsDAO,
    private val traitRemoteToDBOMapper: TraitRemoteToDBOMapper,
    private val setRemoteToDBOMapper: SetRemoteToDBOMapper,
    private val traitDBOtoEntityMapper: TraitDBOtoEntityMapper
) : DetailChampRepository {
    override suspend fun getDetailChamp(champId: String): Either<Failure, ChampDetailEntity> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val champDBO = champDAO.getChampById(champId)
            val items = itemsDAO.getItemByChampId(champId)
            val champDetailHeader =
                detailChampHeaderMapper.mapper(champDBO = champDBO, items = items)
            val traitDBO = traitsDAO.getTraitsByChampId(champId)
            val traitEntity = mutableListOf<ChampDetailEntity.Trait>()
            val sets = setDAO.getSetByChampId(champId)
            val champLeagues = champDAO.getLeagueByChampId(champId)
            traitDBO.forEach { trait ->
                val setDbo: List<SetDBO> = sets.filter { set ->
                    set.traitKey == trait.traitKey
                }
                val leagues = champLeagues.filter {
                    trait.traitKey == it.trait
                }
                traitEntity.add(traitDBOtoEntityMapper.map(trait, setDbo, leagues))
            }
            return@runSuspendWithCatchError Either.Success(
                ChampDetailEntity(
                    detailHeader = champDetailHeader,
                    traits = traitEntity,
                )
            )
        }
}