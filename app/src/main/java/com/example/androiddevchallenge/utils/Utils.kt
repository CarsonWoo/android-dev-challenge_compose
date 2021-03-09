/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private var sDensity = 0F

private val sUiHandler = Handler(Looper.getMainLooper())

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

fun runOnUiThread(action: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        action.invoke()
    } else {
        sUiHandler.post(action)
    }
}
