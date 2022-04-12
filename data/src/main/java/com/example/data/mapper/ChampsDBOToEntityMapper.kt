package com.example.data.mapper

import com.example.common_jvm.extension.createImgUrl
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ChampDBO
import com.example.data.reponse.ChampionResponse
import com.example.domain.entities.ChampsEntity


class ChampsDBOToEntityMapper : Mapper<ChampDBO, ChampsEntity>() {
    override fun map(input: ChampDBO): ChampsEntity {
        return ChampsEntity(
            id = input.id,
            name = input.name,
            imgUrl = input.name,
            cost = input.cost
        )
    }
}