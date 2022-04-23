package com.example.data.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.response.ItemsDBO
import com.example.data.local.service.ItemsDAO
import com.example.data.mapper.items.ItemDBOtoEntityMapper
import com.example.data.mapper.syncDataMappers.ItemsRemoteDBOMapper
import com.example.data.remote.SyncDataApiService
import com.example.data.reponse.ItemResponse
import com.example.domain.entities.ItemEntity
import com.example.domain.repo.ItemRepository

class ItemRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val itemsDAO: ItemsDAO,
    private val syncDataApiService: SyncDataApiService,
    private val itemsRemoteDBOMapper: ItemsRemoteDBOMapper,
    private val itemDBOtoEntityMapper: ItemDBOtoEntityMapper
) : ItemRepository {
    override suspend fun getAllItem(): Either<Failure, List<ItemEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            var itemDBOs: List<ItemsDBO> = itemsDAO.getAllItems()
            if (itemDBOs.isNullOrEmpty()) {
                val result: List<ItemResponse> = syncDataApiService.getItemsList()
                itemDBOs = itemsRemoteDBOMapper.mapList(result)
                itemsDAO.insertAllItems(itemDBOs)
            }
            val sortedBy = itemDBOs.sortedWith(compareBy({(it.isSpecial == true)},{it.isElement != true}, {it.isShadow == true}))
            val itemEntity = itemDBOtoEntityMapper.mapList(sortedBy)
            return@runSuspendWithCatchError Either.Success(itemEntity)
        }
}