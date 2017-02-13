package com.rarnu.xpjni.demo

import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File

/**
 * Created by rarnu on 2/9/17.
 */
class XposedModule : IXposedHookLoadPackage {

    @Throws(Throwable::class)
    override fun handleLoadPackage(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
        if (loadPackageParam.packageName == "com.rarnu.xpjni.demo") {
            if (libPath != "") {
                try {
                    Log.e("XposedModule", "jni library path => $libPath")
                    NativeMethod.load(libPath)
                    val ret = NativeMethod.callJniMethod(100, 200)
                    Log.e("XposedModule", "jni call => $ret")
                } catch (e: Exception) {
                    Log.e("XposedModule", "error: $e")
                }
            }
        }
    }

    val libPath: String
        get() {
            var fullPath = ""
            val pkgPath = "/data/app/com.rarnu.xpjni.demo-"
            val soPath = "/lib/arm/libxpjni.so"
            for (i in 1..2) {
                fullPath = "$pkgPath$i$soPath"
                if (File(fullPath).exists()) {
                    break
                }
            }
            return fullPath
        }
}