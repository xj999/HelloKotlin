package com.example.luxj.hellokotlin.image.listener

import java.io.File

/**
 * @date create 2017/6/1
 * @author Luxj
 * @description
 * @version
 */
interface ImageDownLoadListener {
    fun onDownLoadSuccess(file: File)

    fun onDownLoadFail()
}