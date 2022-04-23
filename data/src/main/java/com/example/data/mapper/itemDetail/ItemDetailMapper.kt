package com.example.data.mapper.itemDetail

import com.example.common_jvm.extension.default
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultFalse
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.service.ItemsDAO
import com.example.domain.entities.ItemDetailEntity

class ItemDetailMapper : Mapper<ItemsDAO.ItemDetailDBO?, ItemDetailEntity>() {
    override fun map(input: ItemsDAO.ItemDetailDBO?): ItemDetailEntity {
        return ItemDetailEntity(
            id = input?.id.defaultZero(),
            itemName = input?.name.defaultEmpty(),
            descriptor = input?.description.defaultEmpty(),
            imgUrl = input?.imgUrl.defaultEmpty(),
            imgPath = input?.imagePath.defaultEmpty(),
            element1Url = input?.element1Url.defaultEmpty(),
            element1Path = input?.element1Path.defaultEmpty(),
            element2Url = input?.element2Url.defaultEmpty(),
            element2Path = input?.element2Path.defaultEmpty(),
            isElement = input?.isElement.defaultFalse()
        )
    }
}