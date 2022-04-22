package com.example.data.mapper.detail

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.service.ChampDAO
import com.example.domain.entities.ChampDetailEntity

class ChampByTraitMapper : Mapper<ChampDAO.ChampByTrait?, ChampDetailEntity.Trait.ChampLeague>() {
    override fun map(input: ChampDAO.ChampByTrait?): ChampDetailEntity.Trait.ChampLeague {
        return ChampDetailEntity.Trait.ChampLeague(
            id = input?.id.defaultEmpty(),
            cost = input?.cost.defaultZero(),
            imgUrl = input?.imgUrl.defaultEmpty(),
            imgPath = input?.imagePath.defaultEmpty(),
        )
    }
}