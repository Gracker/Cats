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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.androidperf.cats.R
import com.androidperf.cats.model.Cat

class DetailPage {
    @Composable
    fun DetailPageScreen(navController: NavController, cat: Cat) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = cat.name
                        )
                    },
                    backgroundColor = Color.White, elevation = 10.dp,
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigate("main_screen") {
                                    popUpTo("main_screen") { inclusive = true }
                                }
                            }
                        ) {
                            val backIcon: Painter = painterResource(R.drawable.ic_back)
                            Icon(painter = backIcon, contentDescription = "ic_back")
                        }
                    }
                )
            },
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtistCard(cat)
            }
        }
    }

    @Composable
    fun ArtistCard(
        cat: Cat,
    ) {
        Column(
            Modifier
                .padding(PaddingValues(horizontal = 8.dp, vertical = 4.dp))
                .verticalScroll(rememberScrollState())
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

            Column(
                Modifier
                    .padding(padding)
            ) {
                val des = stringResource(cat.des)
                Text(des)
            }
        }
    }
}
