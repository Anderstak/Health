package com.example.health

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class MainRegisterActivity : AppCompatActivity() {
    lateinit var editLogin: EditText //называние с маленькой буквы
    lateinit var editPasswordFirst: EditText
    lateinit var editPasswordSecond: EditText
    lateinit var signUp: View
    lateinit var avatar: CircleImageView
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        init()
        signUp.setOnClickListener {
            addNewLogin()
        }

    }

    private fun init() {
        repository = Repository.getInstance()
        editLogin = findViewById(R.id.nickname_input)
        editPasswordFirst = findViewById(R.id.et_password_first)
        editPasswordSecond = findViewById(R.id.et_password_second)
        avatar = findViewById(R.id.image_user)
        signUp = findViewById(R.id.sign_up_btn)
    }

    private fun addNewLogin() {
        if (editPasswordFirst.text.toString().trim().equals(editPasswordSecond.text.toString().trim())){
            repository.password = editPasswordFirst.text.toString().trim()
            repository.userName = editLogin.text.toString().trim()
                //добавил сохранение нового аккаунта сразу в fairbase
            repository.saveDataBase()
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, R.string.wrong_registration, Toast.LENGTH_SHORT).show()
        }
    }

}
