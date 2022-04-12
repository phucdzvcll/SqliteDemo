package com.example.domain.base

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.free.domain.usecases.base.UseCaseParams

abstract class  UseCase<Params : UseCaseParams, Result>() {

    suspend fun execute(params: Params): Either<Failure, Result> {
        return try {
            executeInternal(params)
        } catch (e: Exception) {
            Either.Fail(Failure.UnCatchError(e))
        }
    }

    protected abstract suspend fun executeInternal(params: Params): Either<Failure, Result>
}