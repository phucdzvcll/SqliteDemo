package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.service.ItemsDAO
import com.example.data.mapper.itemDetail.ItemDetailMapper
import com.example.domain.entities.ItemDetailEntity
import com.example.domain.repo.ItemDetailRepository

class ItemDetailRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val itemsDAO: ItemsDAO,
    private val itemDetailMapper: ItemDetailMapper
) : ItemDetailRepository {
    override suspend fun getItemById(itemId: Int): Either<Failure, ItemDetailEntity> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val itemDBO = itemsDAO.getDetailItemById(itemId)
            val entity = itemDetailMapper.map(itemDBO)
            return@runSuspendWithCatchError Either.Success(entity)
        }
}