package com.example.androiddevchallenge.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.DetailActivity
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.DogeData
import com.example.androiddevchallenge.data.generateBannerData
import com.example.androiddevchallenge.data.generateDogeData
import com.example.androiddevchallenge.getStatusBarHeight
import com.example.androiddevchallenge.ui.widget.Author
import com.example.androiddevchallenge.ui.widget.DogeBanner
import com.example.androiddevchallenge.ui.widget.DogeItem

@Composable
fun MainPage(mContext: Context) {
    Log.e("carson", "mContext = $mContext")
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(top = getStatusBarHeight(mContext))) {
        Scaffold(
            Modifier
                .fillMaxWidth()
                .background(Color(0xF8F8F8)),
            topBar = {
                TopAppBar(
                    title = { Text("Doge Space", fontSize = 20.sp, color = Color.Black) },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "search", modifier = Modifier
                                .width(18.dp)
                                .height(18.dp))
                        }
                    },
                    elevation = 5.dp,
                    backgroundColor = Color.White
                )
            },
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 20.dp),
                content = {
                    // header
                    item {
                        Text(text = "Daily Doge Banner", fontFamily = FontFamily.Cursive,
                        fontSize = 30.sp, color = Color.Red, fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start, modifier = Modifier.padding(start = 15.dp))
                    }

                    item {
                        LazyRow(modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            val list = generateBannerData()
                            list.forEachIndexed { position, it ->
                                item {
                                    DogeBanner(item = it, onClick = {
                                        jumpToDetail(mContext, it)
                                    })
                                }
                            }
                        }
                    }

                    item {
//                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Latest News and Reviews", fontFamily = FontFamily.Cursive,
                            fontSize = 30.sp, color = Color.Red, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start, modifier = Modifier.padding(start = 15.dp))
//                        Spacer(modifier = Modifier.padding(5.dp))
                    }

                    generateDogeData().forEach { data ->
                        item {
                            DogeItem(url = data.url, name = data.name, onClick = {
                                jumpToDetail(mContext, data)
//                                (mContext as Activity).overridePendingTransition()
                            })
                        }
                    }

                    // footer
                    item {
                        Author()
                    }
                })
        }

    }
}

private fun jumpToDetail(context: Context, data: DogeData) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra("enterParam", Bundle().apply {
        putString("title", data.name)
        putString("desc", data.desc)
        putString("url", data.url)
    })
    context.startActivity(intent)
}