package com.example.data.mapper

import com.example.common_jvm.extension.createImgUrl
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ChampDBO
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.ChampsEntity


class ChampsRemoteDBOMapper : Mapper<ChampionResponse?, ChampDBO>() {
    override fun map(input: ChampionResponse?): ChampDBO {
        return ChampDBO(
            id = input?.id.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            imgUrl = input?.name.createImgUrl(),
            cost = input?.cost.defaultZero()
        )
    }
}