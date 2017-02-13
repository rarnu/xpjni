package com.rarnu.xpjni.demo

import android.util.Log

/**
 * Created by rarnu on 2/10/17.
 */
object NativeMethod {

    fun load(path: String) {
        try {
            System.load(path)
            Log.e("XposedModule", "jni library loaded")
        } catch (th: Throwable) {
            Log.e("XposedModule", "load jni library error => $th")
        }
    }

    external fun callJniMethod(x: Int, y: Int): Int

}