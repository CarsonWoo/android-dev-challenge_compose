package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Author() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(text = "--------------------------------------------------------", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Text(text = "github.com/CarsonWoo", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), color = Color.Black)
            Text(text = "--------------------------------------------------------", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
    }
}