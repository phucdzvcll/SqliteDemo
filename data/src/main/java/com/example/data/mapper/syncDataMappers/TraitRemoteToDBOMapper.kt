package com.example.data.mapper.syncDataMappers

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.TraitDBO
import com.example.data.reponse.TraitResponseItem
import java.util.*

class TraitRemoteToDBOMapper : Mapper<TraitResponseItem?, TraitDBO>() {
    override fun map(input: TraitResponseItem?): TraitDBO {
        return TraitDBO(
            traitKey = input?.key.defaultEmpty(),
            description = input?.description,
            innate = input?.innate,
            name = input?.name,
            type = input?.type,
            imgUrl = "https://rerollcdn.com/icons/" + input?.name.defaultEmpty().lowercase(
                Locale.ROOT
            ) + ".png",
            imgPath = "traits/item_${input?.name}.png"
        )
    }
}
