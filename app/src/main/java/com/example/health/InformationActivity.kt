package com.example.health

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class InformationActivity : AppCompatActivity() {

    lateinit var buttonBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        init()
        // слушатель на кнопку назад
        buttonBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun init() {
        buttonBack = findViewById(R.id.iv_icon_back)
    }


}


