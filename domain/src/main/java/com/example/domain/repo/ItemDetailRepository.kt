package com.example.domain.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ItemDetailEntity

interface ItemDetailRepository {
    suspend fun getItemById(itemId: Int): Either<Failure, ItemDetailEntity>
}