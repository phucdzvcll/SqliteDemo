package com.example.data.mapper.syncDataMappers

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ItemsDBO
import com.example.domain.entities.ChampDetailEntity

class ItemChampDetailMapper : Mapper<ItemsDBO?, ChampDetailEntity.DetailHeader.Item>() {
    override fun map(input: ItemsDBO?): ChampDetailEntity.DetailHeader.Item {
        return ChampDetailEntity.DetailHeader.Item(
            imgPath = input?.imagePath.defaultEmpty(),
            imgUrl = input?.imgUrl.defaultEmpty(),
            itemName = input?.name.defaultEmpty()
        )
    }
}