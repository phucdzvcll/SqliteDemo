package com.example.domain.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ChampsEntity

interface ChampRepository {
    suspend fun getAllChamps(): Either<Failure, List<ChampsEntity>>
}