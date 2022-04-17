package com.example.domain.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.SyncChampsItemsEntity
import com.example.domain.entities.SyncChampsTrainsEntity
import com.example.domain.entities.SyncListChampsEntity

interface SyncDataRepository {
    suspend fun syncChampsTraits() : Either<Failure, SyncChampsTrainsEntity>
    suspend fun syncChampsItems() : Either<Failure, SyncChampsItemsEntity>
    suspend fun syncListChamps() : Either<Failure, SyncListChampsEntity>
}