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
package com.androidperf.cats

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.androidperf.cats.model.Cat
import com.androidperf.cats.page.DetailPage
import com.androidperf.cats.page.MainPage
import com.androidperf.cats.theme.MyTheme
import com.google.gson.Gson

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarToLight()
        setContent {
            MyTheme {
                ComposeNavigation()
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        val navController = rememberNavController()
        MainPage(navController = navController)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        val navController = rememberNavController()
        MainPage(navController = navController)
    }
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        // main page
        composable("main_screen") {
            MainPage(navController = navController)
        }
        // detail page
        composable(
            "detail_screen/{cat}",
            arguments = listOf(navArgument("cat") { type = NavType.StringType })
        ) { backStackEntry ->
            val mDetailPage = DetailPage()
            backStackEntry.arguments?.getString("cat")?.let { json ->
                val cat = Gson().fromJson(json, Cat::class.java)
                mDetailPage.DetailPageScreen(navController = navController, cat)
            }
        }
    }
}
