package com.example.data.mapper.syncDataMappers

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ChampItemsDBO
import com.example.data.reponse.ChampionResponse

class ChampItemsMapper : Mapper<ChampionResponse, List<ChampItemsDBO>>() {
    override fun map(input: ChampionResponse): List<ChampItemsDBO> {
        val result: MutableList<ChampItemsDBO> = mutableListOf()
        input.items.defaultEmpty().forEach {
            result.add(ChampItemsDBO(
                champ = input.name.defaultEmpty(),
                item = it.defaultEmpty()
            ))
        }
        return result
    }
}