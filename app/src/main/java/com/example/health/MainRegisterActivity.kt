package com.example.health

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainRegisterActivity:AppCompatActivity() {

    lateinit var editLogin : EditText //называние с маленькой буквы
    lateinit var editPassword : EditText
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registr)
    }

}