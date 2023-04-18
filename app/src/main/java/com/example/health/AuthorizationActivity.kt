package com.example.health

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AuthorizationActivity:AppCompatActivity() {
    lateinit var editLogin : EditText //называние с маленькой буквы
    lateinit var editPassword : EditText
    lateinit var buttonUp : Button
    lateinit var buttonRegist : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_auth)
        init()
        buttonRegist.setOnClickListener{
            val intent = Intent(this, MainRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonUp.setOnClickListener {
            login()
        }
        }

    private fun init(){
        editLogin=findViewById(R.id.et_edit_login)
        editPassword=findViewById(R.id.et_edit_password)
        buttonUp=findViewById(R.id.b_sign_in)
        buttonRegist=findViewById(R.id.tv_registration)
    }
    private fun login(){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}