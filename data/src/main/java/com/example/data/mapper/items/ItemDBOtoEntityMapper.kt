package com.example.data.mapper.items

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ItemsDBO
import com.example.domain.entities.ItemEntity

class ItemDBOtoEntityMapper : Mapper<ItemsDBO?, ItemEntity>() {
    override fun map(input: ItemsDBO?): ItemEntity {
        return ItemEntity(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            imgUrl = input?.imgUrl.defaultEmpty(),
            imgPath = input?.imagePath.defaultEmpty(),
        )
    }
}