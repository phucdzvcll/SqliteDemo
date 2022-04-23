package com.example.domain.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ItemEntity

interface ItemRepository {
    suspend fun getAllItem(): Either<Failure, List<ItemEntity>>
}