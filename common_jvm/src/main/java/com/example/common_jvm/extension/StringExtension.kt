package com.example.common_jvm.extension

fun String?.createImgUrl(): String {
    val url = "https://rerollcdn.com/characters/Skin/5/"
    return url + this.defaultEmpty().replace(" ", "").replace("'", "") + ".png"
}

