package com.example.data.repo

import android.util.Log
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.response.ChampDBO
import com.example.data.local.service.ChampDAO
import com.example.data.mapper.ChampsDBOToEntityMapper
import com.example.data.mapper.syncDataMappers.ChampsRemoteDBOMapper
import com.example.data.remote.ChampionApiService
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.ChampsEntity
import com.example.domain.repo.ChampsRepository


class ChampsRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val championApiService: ChampionApiService,
    private val champsDBOToEntityMapper: ChampsDBOToEntityMapper,
    private val champsRemoteDBOMapper: ChampsRemoteDBOMapper,
    private val champDAO: ChampDAO,
) : ChampsRepository {
    override suspend fun getListChamp(): Either<Failure, List<ChampsEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val champsListEntities: MutableList<ChampsEntity> = mutableListOf()
            val champDBOs: List<ChampDBO> = champDAO.getAllChamp()
            if (champDBOs.isNullOrEmpty()) {
                val champsListResponse: List<ChampionResponse> = championApiService.getChampsList()
                Log.d("get champ list", champsListResponse.toString())
                val userDBOS: List<ChampDBO> = champsRemoteDBOMapper.mapList(champsListResponse)
                champDAO.insertChamps(userDBOS)
                champsListEntities.clear()
                champsListEntities.addAll(champsDBOToEntityMapper.mapList(userDBOS))
            } else {
                champsListEntities.clear()
                champsListEntities.addAll(champsDBOToEntityMapper.mapList(champDBOs))
            }
            return@runSuspendWithCatchError Either.Success(champsListEntities)
        }
}