package com.ptr.currency.utils

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.WindowManager


val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

class ScreenUtils {
    fun getScreenHeight(context: Context): Int {
        val windowManager = context
            .getSystemService(WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    fun getScreenHeightInDp(context: Context): Int {
        return getScreenHeight(context).dp
    }
}
