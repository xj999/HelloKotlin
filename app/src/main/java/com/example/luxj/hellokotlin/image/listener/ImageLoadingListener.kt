package com.example.luxj.hellokotlin.image.listener

import android.widget.ImageView

/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
interface ImageLoadingListener {
    fun onSuccess(imageView: ImageView, path: String)
    fun onError(imageView: ImageView, path: String)
}