package com.example.data.mapper.champDetail

import com.example.common_jvm.extension.defaultEmpty
import com.example.data.local.response.SetDBO
import com.example.data.local.response.TraitDBO
import com.example.data.local.service.ChampDAO
import com.example.domain.entities.ChampDetailEntity

class TraitDBOtoEntityMapper(
    private val setDBOtoEntityMapper: SetDBOtoEntityMapper,
    private val champByTraitMapper: ChampByTraitMapper
) {
    fun map(
        input: TraitDBO?,
        sets: List<SetDBO>?,
        league: List<ChampDAO.ChampByTrait>
    ): ChampDetailEntity.Trait {
        return ChampDetailEntity.Trait(
            traitKey = input?.traitKey.defaultEmpty(),
            description = input?.description.defaultEmpty(),
            innate = input?.innate.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            type = input?.type.defaultEmpty(),
            imgUrl = input?.imgUrl.defaultEmpty(),
            imgPath = input?.imgPath.defaultEmpty(),
            sets = setDBOtoEntityMapper.mapList(sets.defaultEmpty()),
            champLeagues = champByTraitMapper.mapList(league)
        )
    }
}