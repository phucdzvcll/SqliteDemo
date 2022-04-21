package com.example.data.mapper.syncDataMappers

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.MapperSuspend
import com.example.data.local.response.ChampDBO
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.ChampsEntity


class ChampsDBOEntityMapper : MapperSuspend<ChampDBO?, ChampsEntity>() {
    override suspend fun map(input: ChampDBO?): ChampsEntity {
        return ChampsEntity(
            id = input?.id.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            imgUrl = createImgUrl(input?.name),
            cost = input?.cost.defaultZero(),
            imgPath = createImageAssets(input),
        )
    }

    private fun createImageAssets(input: ChampDBO?) = "champs/champ_${input?.name}.png"
    private fun createImgUrl(name: String?): String {
        val url = "https://rerollcdn.com/characters/Skin/5/"
        return url + name.defaultEmpty().replace(" ", "").replace("'", "") + ".png"
    }
}