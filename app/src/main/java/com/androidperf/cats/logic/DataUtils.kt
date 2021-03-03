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
package com.androidperf.cats.logic

import com.androidperf.cats.R
import com.androidperf.cats.model.Cat

class DataUtils {
    /**
     * Read the data from dogs.json and parse it into Dog object list to return.
     */
    fun getCats(): List<Cat> {
        val catsList: ArrayList<Cat> = ArrayList()
        catsList.add(Cat("Momo", R.drawable.cats1, "Chris", R.drawable.owner1, R.string.cat1))
        catsList.add(Cat("Kuro", R.drawable.cats2, "Lee", R.drawable.owner2, R.string.cat2))
        catsList.add(Cat("Hana", R.drawable.cats3, "Lina", R.drawable.owner3, R.string.cat3))
        catsList.add(Cat("Koko", R.drawable.cats4, "Carl", R.drawable.owner4, R.string.cat4))
        catsList.add(Cat("Fuko", R.drawable.cats5, "Miksa", R.drawable.owner5, R.string.cat5))
        catsList.add(Cat("Mei", R.drawable.cats6, "Gracker", R.drawable.owner6, R.string.cat6))
        catsList.add(Cat("Cal", R.drawable.cats7, "Sam", R.drawable.owner7, R.string.cat7))
        return catsList
    }
}
