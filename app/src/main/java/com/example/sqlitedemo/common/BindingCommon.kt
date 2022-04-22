package com.example.sqlitedemo.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.common_jvm.extension.defaultEmpty
import java.io.IOException
import java.io.InputStream


object BindingCommon {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url.defaultEmpty())
            .into(imageView)
    }

    @BindingAdapter(value = ["imgPath", "imgUrl"], requireAll = true)
    @JvmStatic
    fun loadImageByBitmap(imageView: ImageView, imgPath: String?, imgUrl: String?) {
        try {
            // get input stream
            val ims: InputStream = imageView.context.assets.open(imgPath.defaultEmpty())
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null)
            // set image to ImageView
            imageView.setImageDrawable(d)
        } catch (ex: IOException) {
            loadImage(imageView, imgUrl)
        }
    }
}