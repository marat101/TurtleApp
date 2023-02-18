package com.turtleteam.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.turtleteam.domain.usecases.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.navigation.NavigationController
import com.turtleteam.ui.screens.navigation.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.TopBar
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.JetTheme
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.utils.TurtlesBackground
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()

    private val navigation: NavigationController by inject()

    @OptIn(ExperimentalPagerApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val isDarkMode = remember { mutableStateOf(getThemeStateUseCase.execute()) }
            val pagerState = rememberPagerState()
            val navController = rememberNavController()
            navigation.navController = navController
            TurtleAppTheme(isDarkMode.value) {
                window.setBackgroundDrawableResource(JetTheme.images.windowBackground)
                Scaffold(
                    topBar = { TopBar(isDarkMode = isDarkMode, saveThemeStateUseCase) },
                    bottomBar = {
                        BottomNavigationMenu(
                            pagerState,
                            navController.currentBackStackEntryAsState()
                        )
                    }
                ) {
                    Box(
                        Modifier.fillMaxWidth().background(JetTheme.color.backgroundBrush)
                    ) {
                        TurtlesBackground()
                        TurtleNavHost(navController, pagerState)
                    }
                }
            }
//            Column(
//                Modifier
//                    .verticalScroll(rememberScrollState())
//                    .background(Color.White)
//            ) {
//                for (i in 1..8)
//                    ImageItemTest()
//                for (i in 1..10)
//                    RoundedshapeTest(i.toString())
//            }
        }
    }
}

@Composable
fun ImageItemTest() {
    Column {
        val roundValue = 20F
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://media.discordapp.net/attachments/970406739776262145/1072577414292578404/20230208_005707.png?width=840&height=575")
                .crossfade(200)
                .diskCachePolicy(CachePolicy.DISABLED)
                .transformations(RoundedCornersTransformation(radius = roundValue))
                .placeholder(R.drawable.placeholder)
                .build(),
        )
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(7.dp)
        ) {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    modifier = Modifier
                        .height(64.dp)
                        .width(64.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 5.dp, end = 8.dp)
            ) {
                Text(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = TextUnit(18F, TextUnitType.Sp),
                    text = "Тестовый текстaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                )
                Text(
                    fontSize = TextUnit(12F, TextUnitType.Sp),
                    text = "Тестовый текстaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                )
            }
        }
        Divider(modifier = Modifier.padding(start = 90.dp))
    }
}

@Composable
@Preview
fun RoundedshapeTest(
    num: String = "1"
) {
    Column {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(7.dp)
        ) {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .border(15.dp, Color.Gray.copy(0.5F), CircleShape),
                )
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = TextUnit(40F, TextUnitType.Sp),
                    color = Color.Gray.copy(0.5F),
                    text = num
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 5.dp, end = 8.dp)
            ) {
                Text(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = TextUnit(18F, TextUnitType.Sp),
                    text = "Тестовый текстaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                )
                Text(
                    fontSize = TextUnit(12F, TextUnitType.Sp),
                    text = "Тестовый текстaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                )
            }
        }
        Divider(modifier = Modifier.padding(start = 90.dp))
    }
}