package com.example.data.mapper

import com.example.common_jvm.extension.createImgUrl
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.ChampsEntity


class ChampsRemoteToEntityMapper : Mapper<ChampionResponse?, ChampsEntity>() {
    override fun map(input: ChampionResponse?): ChampsEntity {
        return ChampsEntity(
            id = input?.id.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            imgUrl = input?.name.createImgUrl(),
            cost = input?.cost.defaultZero()
        )
    }


}