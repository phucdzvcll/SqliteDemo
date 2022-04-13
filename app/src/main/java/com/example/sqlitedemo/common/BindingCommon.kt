package com.example.sqlitedemo.common

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.common_jvm.extension.defaultEmpty

object BindingCommon {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url.defaultEmpty())
            .into(imageView)
    }

    @BindingAdapter(value = ["imgPath","imgUrl"], requireAll = true)
    @JvmStatic
    fun loadImageByBitmap(imageView: ImageView, imgPath: String, imgUrl: String) {
        val myBitmap = BitmapFactory.decodeFile(imgPath)
        if (myBitmap != null) {
            imageView.setImageBitmap(myBitmap)
        }else {
            loadImage(imageView, imgUrl)
        }
    }
}