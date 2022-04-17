package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.base.UseCase
import com.example.domain.entities.ChampsEntity
import com.example.domain.entities.SyncListChampsEntity
import com.example.domain.repo.ChampsRepository
import com.example.domain.repo.SyncDataRepository
import com.free.domain.usecases.base.UseCaseParams

class SyncListChampsUseCase(private val syncDataRepository: SyncDataRepository) :
    UseCase<UseCaseParams.Empty, SyncListChampsEntity>() {
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, SyncListChampsEntity> {
        return syncDataRepository.syncListChamps()
    }
}