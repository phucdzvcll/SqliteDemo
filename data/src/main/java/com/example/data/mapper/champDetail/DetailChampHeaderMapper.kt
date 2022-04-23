package com.example.data.mapper.champDetail

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.data.local.response.ChampDBO
import com.example.data.local.response.ItemsDBO
import com.example.data.mapper.syncDataMappers.ItemChampDetailMapper
import com.example.domain.entities.ChampDetailEntity

class DetailChampHeaderMapper(private val itemDetailMapper: ItemChampDetailMapper) {
    fun mapper(
        items: List<ItemsDBO>?,
        champDBO: ChampDBO?
    ): ChampDetailEntity.DetailHeader {
        return ChampDetailEntity.DetailHeader(
            id = champDBO?.id.defaultEmpty(),
            name = champDBO?.name.defaultEmpty(),
            imgUrl = champDBO?.imgUrl.defaultEmpty(),
            coverUrl = champDBO?.coverUrl.defaultEmpty(),
            coverPath = champDBO?.coverPath.defaultEmpty(),
            cost = champDBO?.cost.defaultZero(),
            imgPath = champDBO?.imagePath.defaultEmpty(),
            items = itemDetailMapper.mapList(items),
        )
    }
}