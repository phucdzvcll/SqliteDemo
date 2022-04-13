package com.example.data.mapper
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ChampDBO
import com.example.domain.entities.ChampsEntity


class ChampsDBOToEntityMapper : Mapper<ChampDBO, ChampsEntity>() {
    override fun map(input: ChampDBO): ChampsEntity {
        return ChampsEntity(
            id = input.id,
            name = input.name,
            imgUrl = input.imgUrl,
            cost = input.cost,
            imgPath = (input.imagePath).defaultEmpty()
        )
    }
}