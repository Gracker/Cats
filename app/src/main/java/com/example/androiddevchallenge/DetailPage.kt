package com.example.androiddevchallenge

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

class DetailPage {
    @Composable
    fun detailPageScreen(navController: NavController, cat : Cats) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Log.v("Gracker",cat.name)
            ArtistCard(cat)
        }
    }

    @Composable
    fun ArtistCard(
        cat: Cats
    ) {
        Column(Modifier
            .padding(PaddingValues(horizontal = 0.dp, vertical = 8.dp))
            .verticalScroll(rememberScrollState())
        ) {
            val padding = 8.dp
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(horizontal = 0.dp, vertical = 2.dp))

            ) {
                val image: Painter = painterResource(id = cat.ownerImage)
                Image(
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .clip(shape = RoundedCornerShape(20))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(20)
                        ),
                    painter = image, contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Column(Modifier
                    .padding(padding)
                    .weight(1f)
                    .align(Alignment.CenterVertically)) {
                    Text(cat.owner)

                    val randoms = (0..60).random()
                    Text("$randoms minutes ago")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(horizontal = 0.dp, vertical = 2.dp))
            ) {
                val catImg: Painter = painterResource(id = cat.image)
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(shape = RoundedCornerShape(4))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(4)
                        ),
                    painter = catImg, contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }

            Column(Modifier
                .padding(padding)
            ){
                val des = stringResource(cat.des)
                Text(des)
            }
        }
    }

}

