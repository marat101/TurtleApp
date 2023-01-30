package com.turtleteam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.theme.JetTheme

val small = 80.dp
val big = 140.dp
@Composable
fun TurtlesBackground(){
    Box(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter){
            Image(modifier = Modifier.size(small), painter = painterResource(id = JetTheme.images.turtleLeft), contentDescription = null )
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd){
            Image(modifier = Modifier.size(small).offset((-10).dp,90.dp), alignment = Alignment.TopEnd,painter = painterResource(id = JetTheme.images.turtleRight), contentDescription = null )
        }
        Box(){
            Image(modifier = Modifier.offset(25.dp,100.dp).size(big),painter = painterResource(id = JetTheme.images.turtleLeft), contentDescription = null )
        }



        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Image(modifier = Modifier.offset(15.dp,(-140).dp).size(small),painter = painterResource(id = JetTheme.images.turtleRight), contentDescription = null )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart){
            Image(modifier = Modifier.offset(50.dp,(-60).dp).size(small),painter = painterResource(id = JetTheme.images.turtleRight), contentDescription = null )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Image(modifier = Modifier.offset((-15).dp,35.dp).size(small),painter = painterResource(id = JetTheme.images.turtleRight), contentDescription = null )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
            Image(modifier = Modifier.offset(0.dp,(-35).dp).size(big),painter = painterResource(id = JetTheme.images.turtleLeft), contentDescription = null )
        }

    }
}