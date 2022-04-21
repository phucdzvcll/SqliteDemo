package com.example.data.mapper.syncDataMappers

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.MapperSuspend
import com.example.data.local.response.ChampDBO
import com.example.data.reponse.ChampionResponse


class ChampsRemoteDBOMapper : MapperSuspend<ChampionResponse?, ChampDBO>() {
    override suspend fun map(input: ChampionResponse?): ChampDBO {
        return ChampDBO(
            id = input?.id.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            imgUrl = createImgUrl(input?.name),
            cost = input?.cost.defaultZero(),
            imagePath = createImageAssets(input),
            coverUrl = createCoverUrl(input),
            coverPath = createCoverAssets(input)
        )
    }

    private fun createCoverUrl(input: ChampionResponse?) =
        "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${input?.name}_0.jpg"

    private fun createCoverAssets(input: ChampionResponse?) = "cover_images/champ_${input?.name}_cover.png"
    private fun createImageAssets(input: ChampionResponse?) = "champ_${input?.name}.png"
    private fun createImgUrl(name: String?): String {
        val url = "https://rerollcdn.com/characters/Skin/5/"
        return url + name.defaultEmpty().replace(" ", "").replace("'", "") + ".png"
    }
}