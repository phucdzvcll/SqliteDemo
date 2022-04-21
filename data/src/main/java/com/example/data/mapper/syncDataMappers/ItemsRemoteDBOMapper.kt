package com.example.data.mapper.syncDataMappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Environment
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultFalse
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.MapperSuspend
import com.example.data.local.response.ItemsDBO
import com.example.data.reponse.ChampionResponse
import com.example.data.reponse.ItemResponse
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ItemsRemoteDBOMapper : MapperSuspend<ItemResponse?, ItemsDBO>() {
    override suspend fun map(input: ItemResponse?): ItemsDBO {
        val s = createListElementId(input?.id.defaultZero(), input?.isElement.defaultFalse())
        var element1 = 0
        var element2 = 0
        if (s.isNotEmpty()) {
            element1 = s[0]
            element2 = s[1]
        }
        return ItemsDBO(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            imgUrl = mapImgUrl(input?.isShadow.defaultFalse(), input?.name.defaultEmpty()),
            imagePath = createImageAssets(input = input),
            description = input?.description.defaultEmpty(),
            shadowBonus = input?.shadowBonus.defaultEmpty(),
            shadowPenalty = input?.shadowPenalty.defaultEmpty(),
            isShadow = input?.isShadow.defaultFalse(),
            isElement = input?.isElement.defaultFalse(),
            element1 = element1,
            element2 = element2,
        )
    }

    private fun createListElementId(id: Int, isElement: Boolean): List<Int> {
        if (isElement) {
            return listOf()
        }
        val ids = mutableListOf<Int>()
        if (id < 1000) {
            ids.add(id / 10)
            ids.add(id % 10)
        } else {
            val newId = id - 1000
            ids.add((newId / 10) + 1000)
            ids.add((newId % 10) + 1000)
        }
        return ids
    }

    private fun mapImgUrl(isShadow: Boolean, name: String): String {
        val param = name.replace(" ", "").replace("'", "")
        return if (isShadow) {
            "https://rerollcdn.com/items/Shadow/$param.png"
        } else {
            "https://rerollcdn.com/items/$param.png"
        }
    }
    private fun createImageAssets(input: ItemResponse?) = "items/item_${input?.name}.png"

}