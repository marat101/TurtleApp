package com.turtleteam.ui.theme_animator

import android.app.Activity
import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.nativeCanvas

fun View.bitmap(
    bounds: Rect
): Bitmap? {

    try {
        val bitmap = Bitmap.createBitmap(
            bounds.width.toInt(),
            bounds.height.toInt(),
            Bitmap.Config.ARGB_8888,
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PixelCopy.request(
                (this.context as Activity).window,
                android.graphics.Rect(
                    bounds.left.toInt(),
                    bounds.top.toInt(),
                    bounds.right.toInt(),
                    bounds.bottom.toInt()
                ),
                bitmap,
                {},
                Handler(Looper.getMainLooper())
            )
        } else {
            val canvas = androidx.compose.ui.graphics.Canvas(bitmap.asImageBitmap()).nativeCanvas
            this.draw(canvas)
            canvas.setBitmap(null)
        }
        return bitmap
    } catch (e: Exception) {
        return null
    }
}