package com.alextsy.shared.util

actual fun getScreenWidth(): Float {
    val displayMetrics = android.content.res.Resources.getSystem().displayMetrics
    return displayMetrics.widthPixels / displayMetrics.density
}