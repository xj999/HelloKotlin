package com.example.luxj.hellokotlin.utils

import android.util.Log

/**
 * Created by luxj on 2017/5/31.
 */
inline fun <reified T> T.d(log: Any){
    Log.d(T::class.simpleName, log.toString())
}
inline fun <reified T> T.e(log: Any){
    Log.e(T::class.simpleName, log.toString())
}