package com.example.androiddevchallenge.page

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
            .padding(top = getStatusBarHeight(LocalContext.current))) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(modifier = Modifier.fillMaxWidth(),
                    elevation = 5.dp,
                    title = {
                        Text(modifier = Modifier.padding(end = 20.dp), text = data.name, color = Color.Black, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(painter = painterResource(id = com.example.androiddevchallenge.R.drawable.ic_back),
                                contentDescription = "back")
                        }
                    },
                    backgroundColor = Color.White
                )
            },
            floatingActionButton = {
                FloatingActionButton(modifier = Modifier.padding(5.dp), backgroundColor = Color(0xFFFFFFFF), onClick = { Toast.makeText(mContext, "clickLike", Toast.LENGTH_SHORT).show() }) {
                    Icon(modifier = Modifier
                        .width(25.dp)
                        .height(25.dp), painter = painterResource(id = com.example.androiddevchallenge.R.drawable.icon_life), contentDescription = "like", tint = Color(0xFFFCAAD4))
                }
            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                GlideImage(data = data.url, modifier = Modifier
                    .fillMaxWidth(), contentDescription = data.name, fadeIn = true)
                Spacer(modifier = Modifier.padding(15.dp))
                Text(modifier = Modifier.padding(start = 20.dp, end = 20.dp), text = data.desc, lineHeight = 25.sp, fontSize = 18.sp, )
            }



        }
    }

}