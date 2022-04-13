package com.example.data.mapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.common_jvm.extension.createImgUrl
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultFalse
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.ChampDBO
import com.example.data.reponse.ChampionResponse
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class ChampsRemoteDBOMapper : Mapper<ChampionResponse?, ChampDBO>() {
    override fun map(input: ChampionResponse?): ChampDBO {
        return ChampDBO(
            id = input?.id.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            imgUrl = input?.name.createImgUrl(),
            cost = input?.cost.defaultZero(),
            imagePath = saveImage(
                image = input?.name.createImgUrl(),
                imageName = input?.name.defaultEmpty(),
            )
        )
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

    private fun saveImage(image: String, imageName: String): String? {
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File("$root/champ")
        return try {
            myDir.mkdirs()
            myDir.createNewFile()
            val fileName = "champ_$imageName.png"
            val file = File(myDir, fileName)
            val out = FileOutputStream(file)
            val bitmap = getBitmapFromURL(image)
            val s: Boolean? = bitmap?.compress(Bitmap.CompressFormat.PNG, 100, out)
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