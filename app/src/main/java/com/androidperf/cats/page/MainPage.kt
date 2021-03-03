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
package com.androidperf.cats.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.androidperf.cats.logic.DataUtils
import com.androidperf.cats.model.Cat
import com.google.gson.Gson

@Composable
fun MainPage(navController: NavController) {
    Surface(color = MaterialTheme.colors.background) {
        val catsList = DataUtils().getCats()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Pick Your Cats",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    backgroundColor = Color.White, elevation = 10.dp,
                )
            },
        ) {
            CatsFeedList(catsList, navController)
        }
    }
}

@Composable
fun CatsFeedList(cats: List<Cat>, navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(cats.size) { index ->
            ArtistCard(
                cats[index],
                onClick = {
                    val catJson = Gson().toJson(cats[index])
                    navController.navigate("detail_screen/$catJson") {}
                }
            )
        }
    }
}

@Composable
fun ArtistCard(
    cat: Cat,
    onClick: () -> Unit,
) {
    Column(
        Modifier
            .padding(PaddingValues(horizontal = 0.dp, vertical = 8.dp))
            .clickable(onClick = onClick)
    ) {
        val padding = 8.dp
        Row(
            verticalAlignment = Alignment.CenterVertically,
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
            Column(
                Modifier
                    .padding(padding)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(cat.owner)

                val randoms = (0..60).random()
                Text("$randoms minutes ago")
            }
        }

        Row(
            modifier = Modifier
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
    }
}
