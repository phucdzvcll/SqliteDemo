package com.example.domain.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.*

interface SyncDataRepository {
    suspend fun syncListChamps() : Either<Failure, SyncDataEntity>
}