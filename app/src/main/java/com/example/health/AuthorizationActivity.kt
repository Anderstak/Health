package com.example.health

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthorizationActivity : AppCompatActivity() {
    lateinit var editLogin: EditText //называние с маленькой буквы
    lateinit var editPassword: EditText
    lateinit var buttonUp: Button
    lateinit var buttonRegist: TextView
    lateinit var repository: Repository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_auth)
        init()
        buttonRegist.setOnClickListener {
            val intent = Intent(this, MainRegisterActivity::class.java)
            startActivity(intent)
        }
        buttonUp.setOnClickListener {
            login()
        }
    }

    private fun init() {
        repository = Repository.getInstance()
        editLogin = findViewById(R.id.et_edit_login)
        editPassword = findViewById(R.id.et_edit_password)
        buttonUp = findViewById(R.id.b_save)
        buttonRegist = findViewById(R.id.tv_registration)
    }
    private fun login() {
        if (editLogin.text.toString().equals(repository.userName) && editPassword.text.toString().equals(repository.password)) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.wrong_login, Toast.LENGTH_SHORT).show()
        }

    }
}