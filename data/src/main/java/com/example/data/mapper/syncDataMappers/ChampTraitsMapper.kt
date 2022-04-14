package com.example.data.mapper.syncDataMappers

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ChampTraitsDBO
import com.example.data.reponse.ChampionResponse

class ChampTraitsMapper : Mapper<ChampionResponse, List<ChampTraitsDBO>>() {
    override fun map(input: ChampionResponse): List<ChampTraitsDBO> {
        val result: MutableList<ChampTraitsDBO> = mutableListOf()
        input.traits.defaultEmpty().forEach {
            result.add(ChampTraitsDBO(
                champ = input.name.defaultEmpty(),
                trait = it.defaultEmpty()
            ))
        }
        return result
    }
}