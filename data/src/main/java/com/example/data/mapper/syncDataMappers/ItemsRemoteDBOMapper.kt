package com.example.data.mapper.syncDataMappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.common_jvm.extension.createImgUrl
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultFalse
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.common_jvm.mapper.MapperSuspend
import com.example.data.local.response.ChampDBO
import com.example.data.local.response.ItemsDBO
import com.example.data.reponse.ChampionResponse
import com.example.data.reponse.ItemResponse
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class ItemsRemoteDBOMapper : MapperSuspend<ItemResponse?, ItemsDBO>() {
    override suspend fun map(input: ItemResponse?): ItemsDBO {
        return ItemsDBO(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            imgUrl = mapImgUrl(input?.isShadow.defaultFalse(), input?.name.defaultEmpty()),
            imagePath = saveImage(
                image = mapImgUrl(input?.isShadow.defaultFalse(), input?.name.defaultEmpty()),
                imageName = input?.name.defaultEmpty(),
                folderName = "Items",
                imgQuality = 24
            ),
            description = input?.description.defaultEmpty(),
            shadowBonus = input?.shadowBonus.defaultEmpty(),
            shadowPenalty = input?.shadowPenalty.defaultEmpty(),
            isShadow = input?.isShadow.defaultFalse(),
            elements = createListElementId(input?.id.defaultZero())?.toInt()
        )
    }

    private fun createListElementId(id: Int): String {
        val ids = mutableListOf<Int>()
        if (id < 1000) {
            ids.add(id / 10)
            ids.add(id % 10)
        } else {
            val newId = id - 1000
            ids.add((newId / 10) + 1000)
            ids.add((newId % 10) + 1000)
        }
        return "${ids[0]},${ids[1]}"
    }

    private fun mapImgUrl(isShadow: Boolean, name: String): String {
        val param = name.replace(" ", "").replace("'", "")
        return if (isShadow) {
            "https://rerollcdn.com/items/Shadow/$param.png"
        } else {
            "https://rerollcdn.com/items/$param.png"
        }
    }

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            null
        }

    }

    private fun saveImage(
        image: String,
        imageName: String,
        folderName: String,
        imgQuality: Int
    ): String? {
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File("$root/$folderName")
        return try {
            myDir.mkdirs()
            myDir.createNewFile()
            val fileName = "item_$imageName.png"
            val file = File(myDir, fileName)
            val out = FileOutputStream(file)
            val bitmap = getBitmapFromURL(image)
            val s: Boolean? = bitmap?.compress(Bitmap.CompressFormat.PNG, imgQuality, out)
            out.flush()
            out.close()
            if (s.defaultFalse()) {
                return file.absolutePath
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }
}