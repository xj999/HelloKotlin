package com.example.luxj.hellokotlin.image.listener

import android.graphics.Bitmap

/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
interface ImageBitmapListener {
    fun getBitmapSuccess(path: String, bitmap: Bitmap)

    fun getBitmapError(path: String)
}