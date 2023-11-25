package com.turtleteam.ui

import android.app.Activity
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.turtleteam.domain.usecases_impl.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.common.views.TurtlesBackground
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.view.TurtleNavHost
import org.koin.android.ext.android.inject
import ru.turtleteam.theme.TurtleAppTheme
import ru.turtleteam.theme.TurtleTheme


class MainActivity : ComponentActivity() {

    private val navigation: NavigationController by inject()
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()
    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isDark = mutableStateOf(getThemeStateUseCase.execute())

        setContent {
            navController = rememberAnimatedNavController()
            navigation.setNavController(navController)
            Surface(color = Color.White) {
                TurtleAppTheme(isDark.value) {
                    val view = LocalView.current
                    window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                    TurtlesBackground()
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        TopBar(
                            onThemeChange = {
                                isDark.value = !isDark.value
                                saveThemeStateUseCase.execute(isDark.value)
                            },
                            navigation.topBarTitle.value
                        )
                        TurtleNavHost(navController)
                    }
                }
            }
        }
    }
}

fun View.screenshot(
    bounds: Rect
): ImageResult {

    try {

        val bitmap = Bitmap.createBitmap(
            bounds.width.toInt(),
            bounds.height.toInt(),
            Bitmap.Config.ARGB_8888,
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Above Android O not using PixelCopy throws exception
            // https://stackoverflow.com/questions/58314397/java-lang-illegalstateexception-software-rendering-doesnt-support-hardware-bit
            PixelCopy.request(
                (this.context as Activity).window,
                bounds.toAndroidRect(),
                bitmap,
                {},
                Handler(Looper.getMainLooper())
            )
        } else {
            val canvas = androidx.compose.ui.graphics.Canvas(bitmap.asImageBitmap()).nativeCanvas
                .apply {
//                    translate(-bounds.left, -bounds.top)
                }
            this.draw(canvas)
            canvas.setBitmap(null)
        }
        return ImageResult.Success(bitmap)
    } catch (e: Exception) {
        return ImageResult.Error(e)
    }
}

sealed interface ImageResult {
    object Initial : ImageResult
    data class Error(val exception: Exception) : ImageResult
    data class Success(val data: Bitmap) : ImageResult
}

fun createViewBitmap(v: View): Bitmap {
    val bitmap = Bitmap.createBitmap(
        1000, 1000,
        Bitmap.Config.ARGB_8888
    )
    val canvas = androidx.compose.ui.graphics.Canvas(bitmap.asImageBitmap())
    v.draw(canvas.nativeCanvas)
    return bitmap
}

object Clippp : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(Path().apply {
            addRect(
                size.toRect().copy(
                    right = size.width / 2
                )
            )
        })
    }

}