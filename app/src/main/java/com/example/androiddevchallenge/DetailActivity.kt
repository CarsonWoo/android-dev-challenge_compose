package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.data.DogeData
import com.example.androiddevchallenge.page.DetailPage

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras?.get("enterParam") as? Bundle
        val title = extras?.getString("title")?: ""
        val url = extras?.getString("url")?: ""
        val desc = extras?.getString("desc")?: ""
        Log.e("Carson", "title = $title, url = $url")
        setContent {
            DetailPage(DogeData(url, title, desc)) { onBackPressed() }
        }
    }
}

