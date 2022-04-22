package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.base.UseCase
import com.example.domain.entities.ChampDetailEntity
import com.example.domain.repo.DetailChampRepository
import com.free.domain.usecases.base.UseCaseParams

class GetDetailChampUseCase(private val detailChampRepository: DetailChampRepository) :
    UseCase<GetDetailChampUseCase.GetDetailChampParam, ChampDetailEntity>() {

    data class GetDetailChampParam(
        val champId: String
    ) : UseCaseParams

    override suspend fun executeInternal(params: GetDetailChampParam): Either<Failure, ChampDetailEntity> {
        return detailChampRepository.getDetailChamp(params.champId)
    }
}