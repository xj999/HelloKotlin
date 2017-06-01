package com.example.luxj.hellokotlin.image

import android.content.Context
import com.example.luxj.hellokotlin.image.listener.ImageDownLoadListener
import android.support.annotation.NonNull
import com.example.luxj.hellokotlin.image.listener.ImageBitmapListener
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.example.luxj.hellokotlin.image.glide.RoundedCornersTransformation
import com.example.luxj.hellokotlin.image.listener.ImageLoadingListener


/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
class MyImageLoader {
    private var mStrategy: BaseImageStrategy? = null

    constructor() {
        mStrategy = GlideStrategy()
    }

    private constructor(strategy: BaseImageStrategy) {
        mStrategy = strategy
    }

    /**
     * 默认加载图片

     * @param placeholder 占位符
     */
    fun loadImage(url: String, @DrawableRes placeholder: Int, imageView: ImageView, imageLoadingListener: ImageLoadingListener) {
        mStrategy!!.loadImage(url, placeholder, imageView, imageLoadingListener)
    }

    /**
     * 默认加载图片

     * @param placeholder 占位符
     */
    fun loadImage(url: String, @DrawableRes placeholder: Int, imageView: ImageView) {
        mStrategy!!.loadImage(url, placeholder, imageView)
    }

    /**
     * 默认加载图片 /使用默认占位符
     */
    fun loadImage(url: String, imageView: ImageView, imageLoadingListener: ImageLoadingListener) {
        mStrategy!!.loadImage(url, imageView, imageLoadingListener)
    }

    /**
     * 默认加载图片 /使用默认占位符
     */
    fun loadImage(url: String, imageView: ImageView) {
        mStrategy!!.loadImage(url, imageView)
    }

    /**
     * 加载自定义圆角图片 默认占位符

     * @param cornerType 自定义圆角类型 CornerType.ALL 4周圆角    CornerType.TOP 头部圆角 具体查看[&lt;tt&gt;RoundedCornersTransformation.CornerType&lt;/tt&gt;][RoundedCornersTransformation.CornerType]
     */
    fun loadCustomRoundImage(url: String, cornerType: RoundedCornersTransformation.CornerType, imageView: ImageView) {
        mStrategy!!.loadCustomRoundImage(url, cornerType, imageView)
    }

    /**
     * 加载圆角图片  无法自定义 默认占位符
     */
    fun loadRoundImage(url: String, imageView: ImageView) {
        mStrategy!!.loadCustomRoundImage(url, RoundedCornersTransformation.CornerType.ALL, imageView)
    }

    /**
     * 加载圆角图片  默认占位符  自定义边框颜色
     */
    fun loadBorderRoundImage(url: String, imageView: ImageView, @ColorRes borderColor: Int) {
        mStrategy!!.loadBorderRoundImage(url, RoundedCornersTransformation.CornerType.ALL, imageView, borderColor)
    }

    /**
     * 加载圆角图片  默认占位符  自定义边框尺寸、颜色
     */
    fun loadBorderRoundImage(url: String, imageView: ImageView, borderWidth: Float, @ColorRes borderColor: Int) {
        mStrategy!!.loadBorderRoundImage(url, RoundedCornersTransformation.CornerType.ALL, imageView, borderWidth, borderColor)
    }

    /**
     * 加载圆角图片，自定义占位符  自定义边框尺寸、颜色
     */
    fun loadBorderRoundImage(url: String, imageView: ImageView, @DrawableRes placeholder: Int, borderWidth: Float, @ColorRes borderColor: Int) {
        mStrategy!!.loadBorderRoundImage(url, RoundedCornersTransformation.CornerType.ALL, imageView, placeholder, borderWidth, borderColor)
    }

    /**
     * 加载圆角图片  自定义占位符  自定义边框颜色
     */
    fun loadBorderRoundImage(url: String, imageView: ImageView, @DrawableRes placeholder: Int, @ColorRes borderColor: Int) {
        mStrategy!!.loadBorderRoundImage(url, RoundedCornersTransformation.CornerType.ALL, imageView, placeholder, borderColor)
    }


    /**
     * 加载圆角图片  无法自定义 默认占位符 自定义圆角角度
     */
    fun loadRoundImage(url: String, imageView: ImageView, radius: Int) {
        mStrategy!!.loadCustomRoundImage(url, radius, RoundedCornersTransformation.CornerType.ALL, imageView)
    }

    /**
     * 加载圆角图片  无法自定义 默认占位符 自定义圆角角度
     */
    fun loadRoundImage(url: String, @DrawableRes placeholder: Int, imageView: ImageView, radius: Int) {
        mStrategy!!.loadCustomRoundImage(url, radius, placeholder, RoundedCornersTransformation.CornerType.ALL, imageView)
    }

    /**
     * 展示圆角图片   自定义占位符

     * @param placeholder 占位符
     */
    fun loadRoundImage(url: String, @DrawableRes placeholder: Int, imageView: ImageView) {
        mStrategy!!.loadRoundImage(url, placeholder, imageView)
    }

    /**
     * 展示圆形图片 自定义占位符

     * @param placeholder 占位符
     */
    fun loadCircleImage(url: String, @DrawableRes placeholder: Int, imageView: ImageView) {
        mStrategy!!.loadCircleImage(url, placeholder, imageView)
    }

    /**
     * 展示圆形图片 自定义占位符 带边框

     * @param placeholder 占位符
     */
    fun loadBorderCircleImage(url: String, @DrawableRes placeholder: Int, @ColorRes borderColor: Int, imageView: ImageView) {
        mStrategy!!.loadBorderCircleImage(url, imageView, placeholder, borderColor)
    }

    /**
     * 展示原型图片 默认占位符
     */
    fun loadCircleImage(url: String, imageView: ImageView) {
        mStrategy!!.loadCircleImage(url, imageView)
    }

    /**
     * 展示原型图片 默认占位符 带边框
     */
    fun loadBorderCircleImage(url: String, imageView: ImageView, @ColorRes borderColor: Int) {
        mStrategy!!.loadBorderCircleImage(url, imageView, borderColor)
    }

    /**
     * 通过url获取bitmap对象 异步回调

     * @param listener 回调
     */
    fun getBitmap(context: Context, url: String, listener: ImageBitmapListener) {
        mStrategy!!.getBitmap(context, url, listener)
    }

    /**
     * 清除硬盘缓存
     */
    fun clearImageDiskCache(context: Context) {
        mStrategy!!.clearImageDiskCache(context)
    }

    /**
     * 清除内存缓存
     */
    fun clearImageMemoryCache(context: Context) {
        mStrategy!!.clearImageMemoryCache(context)
    }

    /**
     * 保存图片到本地  异步回调

     * @param url          图片地址
     * *
     * @param savePath     保存地址
     * *
     * @param saveFileName 文件名称
     * *
     * @param listener     回调
     */
    fun saveImage(context: Context, url: String, savePath: String, saveFileName: String, listener: ImageDownLoadListener) {
        //fixme 尚未测试
        mStrategy!!.saveImage(context, url, savePath, saveFileName, listener)
    }

    companion object {

        private var mInstance: MyImageLoader? = null

        /**
         * 使用自定义策略
         */
        fun getInstance(strategy: BaseImageStrategy): MyImageLoader {
            if (mInstance == null) {
                synchronized(MyImageLoader::class.java) {
                    if (mInstance == null) {
                        mInstance = MyImageLoader(strategy)
                        return mInstance!!
                    }
                }
            }
            return mInstance!!
        }

        /**
         * 使用默认策略
         */
        val instance: MyImageLoader
            get() {
                if (mInstance == null) {
                    synchronized(MyImageLoader::class.java) {
                        if (mInstance == null) {
                            mInstance = MyImageLoader()
                            return mInstance!!
                        }
                    }
                }
                return mInstance!!
            }
    }
}
