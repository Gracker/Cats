package com.example.androiddevchallenge

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

class DetailPage {
    @Composable
    fun detailPageScreen(navController: NavController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Second Screen\n" +
                        "Click me to go to Third Screen",
                color = Color.Yellow,
                style = TextStyle(textAlign = TextAlign.Center),
//                modifier = Modifier.clickable1(onClick = {
//                    // this will navigate to third screen
//                    navController.navigate("main_screen")
//                })
            )
        }
    }

}

