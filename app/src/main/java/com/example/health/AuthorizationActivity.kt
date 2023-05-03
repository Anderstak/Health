package com.example.health

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthorizationActivity : AppCompatActivity() {
    lateinit var editLogin: EditText
    lateinit var editPassword: EditText
    lateinit var buttonUp: Button
    lateinit var buttonRegist: TextView
    lateinit var repository: Repository

    //переменные необходимые для записи на устройстве "осуществлен вход в аккаунт или нет"
    private val SAVED_AUTHORIZATION = "saved_authorization"
    private lateinit var authorization: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_auth)
        init()
        //проверяем intent, усли он false записываем на устройство значение входа как false
        if (!intent.getBooleanExtra(SAVED_AUTHORIZATION, true)) {
            setAuthorized(false)
        }
        //проверяем записанное значение входа на устройстве, передаем его в метод login
        login(authorization.getBoolean(SAVED_AUTHORIZATION, false))
        buttonRegist.setOnClickListener {
            val intent = Intent(this, MainRegisterActivity::class.java)
            startActivity(intent)
        }
        buttonUp.setOnClickListener {
            // разбил на 2 метода
            login(isLogin())
        }
    }

    private fun init() {
        repository = Repository.getInstance()
        //инициализируем объект sharedPreferences
        authorization = getSharedPreferences(SAVED_AUTHORIZATION, Context.MODE_PRIVATE)
        editLogin = findViewById(R.id.et_edit_login)
        editPassword = findViewById(R.id.et_edit_password)
        buttonUp = findViewById(R.id.b_save)
        buttonRegist = findViewById(R.id.tv_registration)
    }

    //тут в принципе без изменений
    private fun isLogin(): Boolean {
        if (editLogin.text.toString().equals(repository.userName) && editPassword.text.toString()
                .equals(repository.password)
        ) {
            return true
        }
        Toast.makeText(this, R.string.wrong_login, Toast.LENGTH_SHORT).show()
        return false
    }

    private fun login(isLoginBoolean: Boolean) {
        if (isLoginBoolean) {
            val intent = Intent(this, MenuActivity::class.java)
                //опять чистим историю бектрейса
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            setAuthorized(true)
        } else {
            Toast.makeText(this, R.string.wrong_login, Toast.LENGTH_SHORT).show()
        }
    }

    fun setAuthorized(value: Boolean) {
        // с помощью класса Edit подключаем SharedPreferences
        val editor = authorization.edit()
        //в увшещк кладем некое value, указываем имя SAVED_AUTHORIZATION
        editor.putBoolean(SAVED_AUTHORIZATION, value)
        // тут перекладываем все значения из editor на устройство
        editor.apply()
    }
}

