package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.mapper.ChampsListMapper
import com.example.data.remote.ChampionApiService
import com.example.domain.entities.ChampsEntity
import com.example.domain.repo.ChampsRepository


class ChampsRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val championApiService: ChampionApiService,
    private val champsListMapper: ChampsListMapper
    ) : ChampsRepository {
    override suspend fun getListChamp(): Either<Failure, List<ChampsEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val champsListResponse = championApiService.getChampsList()
            val champsListEntity = champsListMapper.mapList(champsListResponse)
            return@runSuspendWithCatchError Either.Success(champsListEntity)
        }
}