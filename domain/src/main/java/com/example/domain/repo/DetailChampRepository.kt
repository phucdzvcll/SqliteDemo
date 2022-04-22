package com.example.domain.repo

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ChampDetailEntity

interface DetailChampRepository {
    suspend fun getDetailChamp(champId: String) : Either<Failure, ChampDetailEntity>
}