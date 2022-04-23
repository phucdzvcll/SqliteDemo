package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.response.ChampDBO
import com.example.data.local.service.ChampDAO
import com.example.data.mapper.syncDataMappers.ChampsDBOEntityMapper
import com.example.data.mapper.syncDataMappers.ChampsRemoteDBOMapper
import com.example.data.remote.SyncDataApiService
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.ChampsEntity
import com.example.domain.repo.ChampRepository

class ChampRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val champsDBOEntityMapper: ChampsDBOEntityMapper,
    private val champDAO: ChampDAO,
    private val champsRemoteDBOMapper: ChampsRemoteDBOMapper,
    private val syncDataApiService: SyncDataApiService,

    ) : ChampRepository {
    override suspend fun getAllChamps(): Either<Failure, List<ChampsEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val allChampDBOs = champDAO.getAllChamp()
            if (allChampDBOs.isNullOrEmpty()) {
                val champsListResponse: List<ChampionResponse> = syncDataApiService.getChampsList()
                val userDBOS: List<ChampDBO> = champsRemoteDBOMapper.mapList(champsListResponse)
                champDAO.insertChamps(userDBOS)
            }
            return@runSuspendWithCatchError Either.Success(
                champsDBOEntityMapper.mapList(
                    allChampDBOs
                )
            )
        }
}