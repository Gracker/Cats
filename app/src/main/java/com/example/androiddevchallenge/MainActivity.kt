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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.gson.Gson
import androidx.compose.foundation.clickable as clickable1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ComposeNavigation()
            }
        }
    }
}

@Composable
fun CatsFeedList(cats: List<Cats>, navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(cats.size) { index ->
//            Text(text = "Name: ${cats[index].name}")
            ArtistCard(
                cats[index],
                onClick = {
                    val catJson = Gson().toJson(cats[index])
                    navController.navigate("detail_screen/$catJson")
                }
            )
        }
    }
}

@Composable
fun ArtistCard(
    cat: Cats,
    onClick: () -> Unit,
) {
    Column(
        Modifier
            .padding(PaddingValues(horizontal = 0.dp, vertical = 8.dp))
            .clickable1(onClick = onClick)
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

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        val navController = rememberNavController()
        MainPageScreen(navController = navController)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        val navController = rememberNavController()
        MainPageScreen(navController = navController)
    }
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        // list page
        composable("main_screen") {
            MainPageScreen(navController = navController)
        }
        // detail page
        composable(
            "detail_screen/{cat}",
            arguments = listOf(navArgument("cat") { type = NavType.StringType })
        ) { backStackEntry ->
            val mDetailPage = DetailPage()
            backStackEntry.arguments?.getString("cat")?.let { json ->
                val cat = Gson().fromJson(json, Cats::class.java)
                mDetailPage.detailPageScreen(navController = navController, cat)
            }
        }
    }
}

@Composable
fun MainPageScreen(navController: NavController) {
    Surface(color = MaterialTheme.colors.background) {
        var catsList: ArrayList<Cats> = ArrayList()
        catsList.add(Cats("cats1", R.drawable.cats1, "Chris", R.drawable.owner1, R.string.cat1))
        catsList.add(Cats("cats2", R.drawable.cats2, "Lee", R.drawable.owner2, R.string.cat2))
        catsList.add(Cats("cats3", R.drawable.cats3, "Lina", R.drawable.owner3, R.string.cat3))
        catsList.add(Cats("cats4", R.drawable.cats4, "Carl", R.drawable.owner4, R.string.cat4))
        catsList.add(Cats("cats5", R.drawable.cats5, "Miksa", R.drawable.owner5, R.string.cat5))
        catsList.add(Cats("cats6", R.drawable.cats6, "Gracker", R.drawable.owner6, R.string.cat6))
        catsList.add(Cats("cats7", R.drawable.cats7, "Sam", R.drawable.owner7, R.string.cat7))
        CatsFeedList(catsList, navController)
    }
}
