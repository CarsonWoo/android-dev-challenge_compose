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
package com.example.androiddevchallenge.ui.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.DogeData
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun DogeItem(url: String, name: String, onClick: () -> Unit) {
    val paddingVertical = 12.dp
    val paddingHorizontal = 16.dp
    Card(
        modifier = Modifier
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
            .clickable { onClick.invoke() }
            .fillMaxWidth(),
        backgroundColor = Color.White, elevation = 3.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                data = url,
                modifier = Modifier
                    .fillMaxWidth()
                    .clipToBounds(),
                contentDescription = name,
                fadeIn = true, error = { Log.e("Carson", "error") }
            ) {}
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                textAlign = TextAlign.Center,
                text = name, fontSize = TextUnit.Unspecified
            )
            Spacer(modifier = Modifier.padding(5.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp).padding(top = 8.dp, end = 8.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp)
                    .clip(CircleShape)
                    .background(color = Color.White)
            ) {
                Image(painter = painterResource(id = R.drawable.icon_life), contentDescription = "like", modifier = Modifier.fillMaxSize().padding(5.dp).clipToBounds())
            }
        }
    }
}

@Composable
fun DogeBanner(item: DogeData, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(320.dp)
            .padding(
                start = 20.dp,
                end = 20.dp
            )
            .clickable { onClick.invoke() }
            .clip(RoundedCornerShape(8.dp))
    ) {
        GlideImage(
            data = item.url,
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds(),
            contentDescription = "banner",
            fadeIn = true
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
//            .padding(top = 30.dp)
                .background(color = Color(0xAE808080))
                .clipToBounds(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                color = Color.White,
                fontSize = 15.sp,
            )
        }
    }
}
