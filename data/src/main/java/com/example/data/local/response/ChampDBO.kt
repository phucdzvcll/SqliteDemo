package com.example.data.local.response

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common_jvm.extension.defaultFalse
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@Entity(tableName = "champ")
data class ChampDBO(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgUrl:String,
    val imagePath:String?,
    val cost: Int
){
}
