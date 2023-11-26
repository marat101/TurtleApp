package com.turtleteam.ui.theme_animator

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt


val LocalThemeAnimator =
    staticCompositionLocalOf<ThemeAnimator> { error("theme animator is not provided") }

@Composable
fun ThemeAnimator(
    modifier: Modifier,
    isDark: Boolean,
    onThemeChange: () -> Unit,
    content: @Composable () -> Unit
) {
    var composableBounds by remember { mutableStateOf<Rect?>(null) }

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .then(modifier)
            .onGloballyPositioned {
                composableBounds = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    it.boundsInWindow()
                } else {
                    it.boundsInRoot()
                }
            }
    ) {
        val scope = rememberCoroutineScope()
        val density = LocalDensity.current
        val config = LocalConfiguration.current
        val view = LocalView.current
        val anim = remember(view) {
            ThemeAnimatorImpl(scope, onThemeChange) {
                density.run {
                    view.bitmap(
                        composableBounds ?: Rect(
                            0f,
                            0f,
                            config.screenWidthDp.dp.toPx(),
                            config.screenHeightDp.dp.toPx()
                        )
                    )
                }
            }
        }
        CompositionLocalProvider(LocalThemeAnimator provides anim) { content() }
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .then(if (anim.bitmap.value != null) Modifier.pointerInput(Unit) { detectTapGestures {} } else Modifier)
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .drawWithCache {
                    onDrawBehind {
                        anim.bitmap.value?.let {
                            val radius = getRadius(size, anim.clickOffset)
                            drawImage(it.asImageBitmap())
                            drawCircle(
                                Color.Transparent,
                                if (isDark) radius else (radius * anim.animatable.value),
                                center = anim.clickOffset,
                                blendMode = BlendMode.SrcOut,
                                style = if (isDark) Stroke((radius * anim.animatable.value) * 2) else Fill
                            )
                        }
                    }
                }
        )
    }
}

@Stable
private class ThemeAnimatorImpl(
    val scope: CoroutineScope,
    val onThemeChange: () -> Unit,
    val onCreateImage: () -> Bitmap?,
) : ThemeAnimator {
    val animatable = Animatable(0f)
    var clickOffset = Offset.Zero
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null)

    override fun changeTheme(offset: Offset, animationSpec: AnimationSpec<Float>) {
        if (!animatable.isRunning) {
            scope.launch {
                clickOffset = offset
                onCreateImage().let {
                    if (it == null) {
                        onThemeChange()
                        return@launch
                    }
                    bitmap.value = it
                }
                onThemeChange()
                delay(150)
                animatable.animateTo(1f, animationSpec)
                bitmap.value?.apply {
                    if (!isRecycled)
                        recycle()
                    bitmap.value = null
                }
                animatable.snapTo(0f)
            }
        }
    }
}

private fun getRadius(
    size: Size,
    point: Offset
): Float {
    val tl = Offset.Zero
    val bl = Offset(0f, size.height)
    val tr = Offset(size.width, 0f)
    val tb = Offset(size.width, size.height)

    return maxOf(
        tl.getDistance(point),
        bl.getDistance(point),
        tr.getDistance(point),
        tb.getDistance(point)
    )
}

private fun Offset.getDistance(point: Offset) = sqrt(
    (x - point.x).toDouble().pow(2.toDouble()) + (point.y - y).toDouble()
        .pow(2.toDouble())
).toFloat()

interface ThemeAnimator {
    fun changeTheme(offset: Offset = Offset.Zero, animationSpec: AnimationSpec<Float> = tween(400))
}