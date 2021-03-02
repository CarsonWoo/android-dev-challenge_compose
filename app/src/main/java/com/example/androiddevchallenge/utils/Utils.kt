package com.example.androiddevchallenge

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private var sDensity = 0F

private fun getDensity(context: Context): Float {
    if (sDensity == 0F) {
        sDensity = context.resources.displayMetrics.density
    }
    return sDensity
}

fun px2dip(context: Context, pxValue: Float): Int {
    return (pxValue / getDensity(context) + 0.5F).toInt()
}

fun getStatusBarHeight(context: Context): Dp {
    val statusBarHeightPx = Resources.getSystem().getDimension(
        Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
    )
    return px2dip(context, statusBarHeightPx).dp
}