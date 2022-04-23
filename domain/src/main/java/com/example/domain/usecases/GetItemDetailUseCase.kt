package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.base.UseCase
import com.example.domain.entities.ItemDetailEntity
import com.example.domain.repo.ItemDetailRepository
import com.free.domain.usecases.base.UseCaseParams

class GetItemDetailUseCase(private val itemDetailRepository: ItemDetailRepository) :
    UseCase<GetItemDetailUseCase.GetItemDetailParam, ItemDetailEntity>() {

    data class GetItemDetailParam(val itemId: Int) : UseCaseParams

    override suspend fun executeInternal(params: GetItemDetailParam): Either<Failure, ItemDetailEntity> {
        return itemDetailRepository.getItemById(params.itemId)
    }
}