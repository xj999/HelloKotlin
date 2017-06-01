package com.example.luxj.hellokotlin.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.luxj.hellokotlin.image.glide.GlideLoader
import com.example.luxj.hellokotlin.image.glide.RoundedCornersTransformation
import com.example.luxj.hellokotlin.image.listener.ImageBitmapListener
import com.example.luxj.hellokotlin.image.listener.ImageDownLoadListener
import com.example.luxj.hellokotlin.image.listener.ImageLoadingListener


/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
class GlideStrategy : GlideLoader(), BaseImageStrategy {
    override fun loadImage(url: String, placeholder: Int, imageView: ImageView, imageLoadingListener: ImageLoadingListener) {
        loadDefault(url, imageView, placeholder, imageLoadingListener)
    }

    override fun loadImage(url: String, placeholder: Int, imageView: ImageView) {
        loadDefault(url, imageView, placeholder, null)
    }

    override fun loadImage(url: String, imageView: ImageView, imageLoadingListener: ImageLoadingListener) {
        loadDefault(url, imageView, 0, imageLoadingListener)
    }

    override fun loadImage(url: String, imageView: ImageView) {
        loadDefault(url, imageView, 0, null)
    }

    override fun loadRoundImage(url: String, imageView: ImageView) {
        loadBorderRoundImage(url, 3, imageView, RoundedCornersTransformation.CornerType.ALL, 0, 0f, 0)
    }

    override fun loadRoundImage(url: String, placeholder: Int, imageView: ImageView) {
        loadBorderRoundImage(url, 3, imageView, RoundedCornersTransformation.CornerType.ALL, placeholder, 0f, 0)
    }

    override fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, borderColor: Int) {
        loadBorderRoundImage(url, 3, imageView, cornerType, 0, 0f, borderColor)
    }

    override fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, placeholder: Int, borderColor: Int) {
        loadBorderRoundImage(url, 3, imageView, cornerType, placeholder, 0f, borderColor)
    }

    override fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, borderWidth: Float, borderColor: Int) {
        loadBorderRoundImage(url, 3, imageView, cornerType, 0, borderWidth, borderColor)
    }

    override fun loadBorderRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView, placeholder: Int, borderWidth: Float, borderColor: Int) {
        loadBorderRoundImage(url, 3, imageView, cornerType, placeholder, borderWidth, borderColor)
    }

    override fun loadCustomRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView) {
        loadBorderRoundImage(url, 3, imageView, cornerType, 0, 0f, 0)
    }

    override fun loadCustomRoundImage(url: String, radius: Int, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView) {
        loadBorderRoundImage(url, radius, imageView, cornerType, 0, 0f, 0)
    }

    override fun loadCustomRoundImage(url: String, radius: Int, placeholder: Int, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView) {
        loadBorderRoundImage(url, radius, imageView, cornerType, placeholder, 0f, 0)
    }

    /**
     * 显示圆形图片

     * @param url         地址
     * @param placeholder 自定义占位符
     */
    override fun loadCircleImage(url: String, placeholder: Int, imageView: ImageView) {
        loadBorderCirclePic(url, imageView, placeholder, 0)
    }

    /**
     * 显示圆形图片 默认占位符
     * @param url 地址
     */
    override fun loadCircleImage(url: String, imageView: ImageView) {
        loadBorderCirclePic(url, imageView, 0, 0)
    }

    override fun loadBorderCircleImage(url: String, imageView: ImageView, color: Int) {
        loadBorderCirclePic(url, imageView, 0, color)
    }

    override fun loadBorderCircleImage(url: String, imageView: ImageView, placeholder: Int, color: Int) {
        loadBorderCirclePic(url, imageView, placeholder, color)
    }

    override fun getBitmap(context: Context, url: String, listener: ImageBitmapListener) {
        getBitmapForUrl(context, url, listener)
    }


    /**
     * 清除硬盘缓存
     */
    override fun clearImageDiskCache(context: Context) {
        clearDiskCache(context)
    }

    /**
     * 清除内存缓存
     */
    override fun clearImageMemoryCache(context: Context) {
        Glide.get(context).clearMemory()
    }

    /**
     * 下载图片
     * @param url          图片地址
     * @param Path         保存地址
     * @param saveFileName 保存名称
     * @param listener     回调
     */
    override fun saveImage(context: Context, url: String, Path: String, saveFileName: String, listener: ImageDownLoadListener) {
        downLoadImage(context, url, Path, saveFileName, listener)
    }
}
