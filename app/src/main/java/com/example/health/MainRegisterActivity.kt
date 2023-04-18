package com.example.health

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView

class MainRegisterActivity : AppCompatActivity() {
    lateinit var editLogin: EditText //называние с маленькой буквы
    lateinit var editPasswordFirst: EditText
    lateinit var editPasswordSecond: EditText
    lateinit var buttonUp: ViewGroup
    lateinit var avatar: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        init()
        buttonUp.setOnClickListener {
            addNewLogin()
        }
    }

    private fun init() {
        editLogin = findViewById(R.id.nickname_input)
        editPasswordFirst = findViewById(R.id.et_password_first)
        buttonUp = findViewById<ViewGroup>(R.id.b_sign_in)
        editPasswordSecond = findViewById(R.id.et_password_second)
        avatar = findViewById(R.id.image_user)
    }

    private fun addNewLogin() {
        var passwordFirst: String = editPasswordFirst.text.toString()
        var passwordSecond: String = editPasswordSecond.text.toString()
        var login: String = editLogin.text.toString()
        if (passwordFirst.equals(passwordSecond)) {

        }

    }

}
