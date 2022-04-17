package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.base.UseCase
import com.example.domain.entities.SyncChampsItemsEntity
import com.example.domain.repo.SyncDataRepository
import com.free.domain.usecases.base.UseCaseParams


class SyncChampionsItemsUseCase(private val syncDataRepository: SyncDataRepository) :
    UseCase<UseCaseParams.Empty, SyncChampsItemsEntity>() {
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, SyncChampsItemsEntity> {
        return syncDataRepository.syncChampsItems()
    }
}