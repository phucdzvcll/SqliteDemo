package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.response.ChampDBO
import com.example.data.local.service.ChampDAO
import com.example.data.mapper.ChampsDBOToEntityMapper
import com.example.data.mapper.ChampsRemoteDBOMapper
import com.example.data.mapper.ChampsRemoteToEntityMapper
import com.example.data.remote.ChampionApiService
import com.example.domain.entities.ChampsEntity
import com.example.domain.repo.ChampsRepository


class ChampsRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val championApiService: ChampionApiService,
    private val champsRemoteToEntityMapper: ChampsRemoteToEntityMapper,
    private val champsDBOToEntityMapper: ChampsDBOToEntityMapper,
    private val champsRemoteDBOMapper: ChampsRemoteDBOMapper,
    private val champDAO: ChampDAO,
) : ChampsRepository {
    override suspend fun getListChamp(): Either<Failure, List<ChampsEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val champDBOs: List<ChampDBO> = champDAO.getAllChamp()
            if (champDBOs.isNullOrEmpty()) {
                val champsListResponse = championApiService.getChampsList()
                val champsListEntity = champsRemoteToEntityMapper.mapList(champsListResponse)
                champDAO.insertChamps(champsRemoteDBOMapper.mapList(champsListResponse))
                return@runSuspendWithCatchError Either.Success(champsListEntity)
            } else {
                val champEntities: List<ChampsEntity> = champsDBOToEntityMapper.mapList(champDBOs)
                return@runSuspendWithCatchError Either.Success(champEntities)
            }
        }
}