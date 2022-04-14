package com.example.common_jvm.mapper

abstract class MapperSuspend<Input, Output> {
    abstract suspend fun map(input: Input): Output

    suspend fun mapList(inputs: List<Input>?): List<Output> {
        val outputs = mutableListOf<Output>()
        inputs?.forEach { input: Input ->
            outputs.add(map(input))
        }
        return outputs
    }
}