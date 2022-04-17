package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.base.UseCase
import com.example.domain.entities.ChampsEntity
import com.example.domain.repo.ChampsRepository
import com.free.domain.usecases.base.UseCaseParams

class SyncListChampsUseCase(private val champsRepository: ChampsRepository) :
    UseCase<UseCaseParams.Empty, List<ChampsEntity>>() {
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, List<ChampsEntity>> {
        return champsRepository.getListChamp()
    }
}