package com.example.luxj.hellokotlin.image.glide

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.example.luxj.hellokotlin.image.listener.ImageBitmapListener
import com.example.luxj.hellokotlin.image.listener.ImageDownLoadListener
import com.example.luxj.hellokotlin.image.listener.ImageLoadingListener
import com.example.luxj.hellokotlin.utils.DimensUtil
import java.io.*

/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
abstract class GlideLoader {
    protected fun clearDiskCache(context: Context) {
        object : Thread() {
            override fun run() {
                Glide.get(context).clearDiskCache()
            }
        }.start()

    }

    /**
     * 加载圆形图片 带占位符带边框
     */
    protected fun loadBorderCirclePic(path: String, imageView: ImageView, placeholderid: Int, color: Int) {
        var placeholderid = placeholderid
        var color = color
        if (placeholderid == 0) {
            placeholderid = getDefaultCircleImage()
        }
        if (color != 0)
            color = ContextCompat.getColor(imageView.context, color)
        Glide.with(imageView.context).load(path).bitmapTransform(GlideCircleTransform(imageView.context, 2, color)).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholderid).fallback(placeholderid).dontAnimate().into(imageView)
    }

    /**
     * 加载自定义圆角图片 可选上圆角 下圆角   坐上圆角 等等
     */
    protected fun loadBorderRoundImage(path: String, radius: Int, imageView: ImageView, cornerType: RoundedCornersTransformation.CornerType, placeholder: Int, borderWidth: Float, borderColor: Int) {
        var radius = radius
        var placeholder = placeholder
        var borderColor = borderColor
        if (radius == 0) {
            radius = 3
        }
        if (placeholder == 0) {
            placeholder = getDefaultImage()
        }
        if (borderColor != 0) {
            borderColor = ContextCompat.getColor(imageView.context, borderColor)
        }
        Glide.with(imageView.context)
                .load(path)
                .bitmapTransform(CenterCrop(imageView.context), RoundedCornersTransformation(imageView.context, DimensUtil.dpToPixels(imageView.context, radius.toFloat()), 0,
                        cornerType, borderWidth, borderColor))
                .placeholder(placeholder)
                .into(imageView)
    }

    /**
     * 默认

     * @param placeholder_id 占位符资源id
     */
    protected fun loadDefault(path: String, imageView: ImageView, placeholder_id: Int, imageLoadingListener: ImageLoadingListener?) {
        var placeholder_id = placeholder_id
        val requestListener = object : RequestListener<String, GlideDrawable> {
            override fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                imageLoadingListener?.onError(imageView, path)
                return false
            }

            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                imageLoadingListener?.onSuccess(imageView, path)
                return false
            }
        }

        val gifRequestListener = object : RequestListener<String, GifDrawable> {
            override fun onException(e: Exception, model: String, target: Target<GifDrawable>, isFirstResource: Boolean): Boolean {
                imageLoadingListener?.onError(imageView, path)
                return false
            }

            override fun onResourceReady(resource: GifDrawable, model: String, target: Target<GifDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                imageLoadingListener?.onSuccess(imageView, path)
                return false
            }
        }
        if (placeholder_id == 0) {
            placeholder_id = getDefaultImage()
        }
        if (getFileType(path).toLowerCase() == "gif") {
            Glide.with(imageView.context).load(path).asGif().centerCrop().placeholder(placeholder_id).fallback(placeholder_id).dontAnimate().listener(gifRequestListener).into(imageView)
        } else {
            Glide.with(imageView.context).load(path).centerCrop().placeholder(placeholder_id).fallback(placeholder_id).dontAnimate().listener(requestListener).into(imageView)
        }
    }

    protected fun getBitmapForUrl(context: Context, url: String, listener: ImageBitmapListener) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                        if (resource != null) listener.getBitmapSuccess(url, resource)
                    }

                    override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                        listener.getBitmapError(url)
                    }
                })
    }

    /**
     * 回调的地址不是在UI线程完成
     */
    protected fun downLoadImage(
            context: Context,
            url: String, savePath: String, name: String,
            listener: ImageDownLoadListener) {
        if (TextUtils.isEmpty(url)) {
            listener.onDownLoadFail()
            return
        }
        var fromStream: InputStream? = null
        var toStream: OutputStream? = null
        try {
            val cacheFile = Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get()
            if (cacheFile == null || !cacheFile.exists()) {
                listener.onDownLoadFail()
                return
            }
            val dir = File(savePath)
            val dirMark: Boolean
            dirMark = dir.exists() || dir.mkdir()
            if (dirMark) {
                val file = File(dir, name + getFileType(cacheFile.absolutePath))
                fromStream = FileInputStream(cacheFile)
                toStream = FileOutputStream(file)
                val length: ByteArray = ByteArray(1024)
                var count: Int = fromStream.read(length)
                while (count > 0) {
                    toStream.write(length, 0, count)
                    count = fromStream.read(length)
                }
                //用广播通知相册进行更新相册
                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                val uri = Uri.fromFile(file)
                intent.data = uri
                context.sendBroadcast(intent)
                listener.onDownLoadSuccess(file)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onDownLoadFail()
        } finally {
            if (fromStream != null) {
                try {
                    fromStream.close()
                    assert(toStream != null)
                    toStream!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    /**
     * 设置默认占位图
     */
    private fun getDefaultImage(): Int {
        return -1
    }

    /**
     * 设置默认圆形占位图
     */
    private fun getDefaultCircleImage(): Int {
        return 0
    }

    /**
     * 获取文件类型
     */
    private fun getFileType(url: String?): String {
        if (url == null || url == "") {
            return ""
        }
        val typeIndex = url.lastIndexOf(".")
        if (typeIndex != -1) {
            return url.substring(typeIndex + 1).toLowerCase()
        }

        return ""
    }
}