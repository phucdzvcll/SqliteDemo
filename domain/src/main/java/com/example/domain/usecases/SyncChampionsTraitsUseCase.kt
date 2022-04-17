package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.base.UseCase
import com.example.domain.entities.SyncChampsTrainsEntity
import com.example.domain.repo.SyncDataRepository
import com.free.domain.usecases.base.UseCaseParams

class SyncChampionsTraitsUseCase(val syncDataRepository: SyncDataRepository) : UseCase<UseCaseParams.Empty, SyncChampsTrainsEntity>() {
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, SyncChampsTrainsEntity> {
        return syncDataRepository.syncChampsTraits()
    }
}