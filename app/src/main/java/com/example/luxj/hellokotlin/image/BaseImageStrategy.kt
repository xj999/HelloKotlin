package com.example.luxj.hellokotlin.image

import android.content.Context
import android.widget.ImageView
import com.example.luxj.hellokotlin.image.listener.ImageDownLoadListener
import com.example.luxj.hellokotlin.image.listener.ImageBitmapListener
import com.example.luxj.hellokotlin.image.glide.RoundedCornersTransformation
import com.example.luxj.hellokotlin.image.listener.ImageLoadingListener


/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
interface BaseImageStrategy {
    fun loadImage(url: String, placeholder: Int, imageView: ImageView, imageLoadingListener: ImageLoadingListener)

    fun loadImage(url: String, placeholder: Int, imageView: ImageView)

    fun loadImage(url: String, imageView: ImageView, imageLoadingListener: ImageLoadingListener)

    fun loadImage(url: String, imageView: ImageView)

    fun loadRoundImage(url: String, imageView: ImageView)

    fun loadRoundImage(url: String, placeholder: Int, imageView: ImageView)

    fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, borderColor: Int)

    fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, placeholder: Int, borderColor: Int)

    fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, borderWidth: Float, borderColor: Int)

    fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, placeholder: Int, borderWidth: Float, borderColor: Int)


    fun loadCustomRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView)

    fun loadCustomRoundImage(url: String, radius: Int, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView)

    fun loadCustomRoundImage(url: String, radius: Int, placeholder: Int, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView)

    fun loadCircleImage(url: String, placeholder: Int, imageView: ImageView)

    fun loadCircleImage(url: String, imageView: ImageView)

    fun loadBorderCircleImage(url: String, imageView: ImageView, color: Int)

    fun loadBorderCircleImage(url: String, imageView: ImageView, placeholder: Int, color: Int)

    fun getBitmap(context: Context, url: String, listener: ImageBitmapListener)

    fun clearImageDiskCache(context: Context)

    fun clearImageMemoryCache(context: Context)

    fun saveImage(context: Context, url: String, savePath: String, saveFileName: String, listener: ImageDownLoadListener)
}