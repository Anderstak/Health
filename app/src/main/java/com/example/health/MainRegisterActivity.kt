package com.example.health

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
    lateinit var urlImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        init()
        signUp.setOnClickListener {
            addNewLogin()
        }
        avatar.setOnClickListener {
            addAvatar()
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
        if (editPasswordFirst.text.toString().equals(editPasswordSecond.text.toString())){
            repository.password = editPasswordFirst.text.toString()
            repository.userName = editLogin.text.toString()
            repository.imageUrl = urlImage
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, R.string.wrong_registration, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addAvatar() {
        val layoutInflater = LayoutInflater.from(this)
        val pompt = layoutInflater.inflate(R.layout.fast_text_entry, null)
        val dialogBilder = AlertDialog.Builder(this)
        dialogBilder.setView(pompt)
        val userInput : EditText = pompt.findViewById(R.id.input_text)
        val negativeButton = dialogBilder.setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                urlImage = userInput.text.toString()
                Glide.with(this)
                    .load(urlImage)
                    .into(avatar)
            }
            .setNegativeButton("Отмена") { dialog, id ->
                dialog.cancel()
            }
        dialogBilder.create().show()
    }

}
