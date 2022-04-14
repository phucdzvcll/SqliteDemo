package com.example.data.repo

import android.util.Log
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.service.ChampDAO
import com.example.data.local.service.ChampTraitsDAO
import com.example.data.mapper.syncDataMappers.ChampTraitsMapper
import com.example.data.remote.SyncDataApiService
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.SyncDataEntity
import com.example.domain.entities.SyncDataStatus
import com.example.domain.repo.SyncDataRepository

class SyncDataRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val syncDataApiService: SyncDataApiService,
    private val champDAO: ChampDAO,
    private val champTraitsDAO: ChampTraitsDAO,
    private val champTraitsMapper: ChampTraitsMapper,
) : SyncDataRepository {
    override suspend fun syncData(): Either<Failure, SyncDataEntity> =
        Either.runSuspendWithCatchError(listOf(remoteExceptionInterceptor)) {
            val champsListResponse: List<ChampionResponse> = syncDataApiService.getChampsList()
            val champTrainDBO = champTraitsMapper.mapList(champsListResponse)
            champTrainDBO.forEach { champTraitsDAO.insertChampsTraits(it) }
            Log.d("get champ list", champTrainDBO.toString())
            return@runSuspendWithCatchError Either.Success(SyncDataEntity(syncDataStatus = SyncDataStatus.Success))
        }
}