package com.turtleteam.ui.utils.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.theme.TurtleTheme

val small = 80.dp
val big = 140.dp

@Composable
fun TurtlesBackground() {
    Box(Modifier.fillMaxSize().padding(vertical = 60.dp).background(TurtleTheme.color.backgroundBrush)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Image(
                modifier = Modifier.size(small),
                painter = painterResource(id = TurtleTheme.images.turtleLeft),
                contentDescription = null
            )
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
            Image(
                modifier = Modifier
                    .size(small)
                    .offset((-10).dp, 90.dp),
                alignment = Alignment.TopEnd,
                painter = painterResource(id = TurtleTheme.images.turtleRight),
                contentDescription = null
            )
        }
        Box() {
            Image(
                modifier = Modifier
                    .offset(25.dp, 100.dp)
                    .size(big),
                painter = painterResource(id = TurtleTheme.images.turtleLeft),
                contentDescription = null
            )
        }



        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                modifier = Modifier
                    .offset(15.dp, (-140).dp)
                    .size(small),
                painter = painterResource(id = TurtleTheme.images.turtleRight),
                contentDescription = null
            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
            Image(
                modifier = Modifier
                    .offset(50.dp, (-60).dp)
                    .size(small),
                painter = painterResource(id = TurtleTheme.images.turtleRight),
                contentDescription = null
            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                modifier = Modifier
                    .offset((-15).dp, 35.dp)
                    .size(small),
                painter = painterResource(id = TurtleTheme.images.turtleRight),
                contentDescription = null
            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            Image(
                modifier = Modifier
                    .offset(0.dp, (-35).dp)
                    .size(big),
                painter = painterResource(id = TurtleTheme.images.turtleLeft),
                contentDescription = null
            )
        }

    }
}