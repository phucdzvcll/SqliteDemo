package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.response.ChampDBO
import com.example.data.local.response.ItemsDBO
import com.example.data.local.response.SetDBO
import com.example.data.local.response.TraitDBO
import com.example.data.local.service.ChampDAO
import com.example.data.local.service.ItemsDAO
import com.example.data.local.service.SetDAO
import com.example.data.local.service.TraitsDAO
import com.example.data.mapper.champDetail.DetailChampHeaderMapper
import com.example.data.mapper.champDetail.TraitDBOtoEntityMapper
import com.example.domain.entities.ChampDetailEntity
import com.example.domain.repo.DetailChampRepository

class DetailChampRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val champDAO: ChampDAO,
    private val detailChampHeaderMapper: DetailChampHeaderMapper,
    private val itemsDAO: ItemsDAO,
    private val setDAO: SetDAO,
    private val traitsDAO: TraitsDAO,
    private val traitDBOtoEntityMapper: TraitDBOtoEntityMapper
) : DetailChampRepository {
    override suspend fun getDetailChamp(champId: String): Either<Failure, ChampDetailEntity> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val champDBO: ChampDBO? = champDAO.getChampById(champId)
            val items: List<ItemsDBO> = itemsDAO.getItemByChampId(champId)
            val champDetailHeader =
                detailChampHeaderMapper.mapper(champDBO = champDBO, items = items)
            val traitDBO: List<TraitDBO> = traitsDAO.getTraitsByChampId(champId)
            val traitEntity: MutableList<ChampDetailEntity.Trait> =
                mutableListOf()
            val sets: List<SetDBO> = setDAO.getSetByChampId(champId)
            val champLeagues: List<ChampDAO.ChampByTrait> = champDAO.getLeagueByChampId(champId)
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