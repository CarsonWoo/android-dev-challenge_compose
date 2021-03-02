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
package com.example.androiddevchallenge.page

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.DogeData
import com.example.androiddevchallenge.getStatusBarHeight
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun DetailPage(data: DogeData, onBackPressed: () -> Unit) {
    val mContext = LocalContext.current
    Surface(
        Modifier
            .fillMaxSize()
            .padding(top = getStatusBarHeight(LocalContext.current))
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 5.dp,
                    title = {
                        Text(modifier = Modifier.padding(end = 20.dp), text = data.name, color = Color.Black, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                painter = painterResource(id = com.example.androiddevchallenge.R.drawable.ic_back),
                                contentDescription = "back"
                            )
                        }
                    },
                    backgroundColor = Color.White
                )
            },
            floatingActionButton = {
                FloatingActionButton(modifier = Modifier.padding(5.dp), backgroundColor = Color(0xFFFFFFFF), onClick = { Toast.makeText(mContext, "clickLike", Toast.LENGTH_SHORT).show() }) {
                    Icon(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        painter = painterResource(id = com.example.androiddevchallenge.R.drawable.icon_life), contentDescription = "like", tint = Color(0xFFFCAAD4)
                    )
                }
            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                GlideImage(
                    data = data.url,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentDescription = data.name, fadeIn = true
                )
                Spacer(modifier = Modifier.padding(15.dp))
                Text(modifier = Modifier.padding(start = 20.dp, end = 20.dp), text = data.desc, lineHeight = 25.sp, fontSize = 18.sp,)
            }
        }
    }
}
