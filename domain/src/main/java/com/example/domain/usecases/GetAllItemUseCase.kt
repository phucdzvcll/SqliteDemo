package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.base.UseCase
import com.example.domain.entities.ItemEntity
import com.example.domain.repo.ItemRepository
import com.free.domain.usecases.base.UseCaseParams

class GetAllItemUseCase(private val itemRepository: ItemRepository) : UseCase<UseCaseParams.Empty, List<ItemEntity>>(){
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, List<ItemEntity>> {
        return itemRepository.getAllItem()
    }
}