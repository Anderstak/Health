package com.example.health

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Integer.parseInt

class ProfileActivity : AppCompatActivity() {
    lateinit var repository: Repository
    lateinit var buttonBack: ImageView
    lateinit var buttonLogout: ImageView
    lateinit var imageAvatar: CircleImageView
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var inGymValue: TextView
    lateinit var inGymDecrement: TextView
    lateinit var inGymIncrement: TextView
    lateinit var outdoorValue: TextView
    lateinit var outdoorDecrement: TextView
    lateinit var outdoorIncrement: TextView
    lateinit var healthyEatingValue: TextView
    lateinit var healthyEatingDecrement: TextView
    lateinit var healthyEatingIncrement: TextView
    lateinit var buttonSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        init()
        loadValueFromRep()
        fillingInResults()
        buttonSave.setOnClickListener {
            saveValueInRep()
        }
        buttonLogout.setOnClickListener{
            val intent = Intent(this, AuthorizationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //TODO
            startActivity(intent)
            finish()
        }
        buttonBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun init() {
        repository = Repository.getInstance()
        buttonBack = findViewById(R.id.iv_icon_back)
        buttonLogout = findViewById(R.id.iv_logout)
        imageAvatar = findViewById(R.id.image_user)
        name = findViewById(R.id.nickname_input)
        email = findViewById(R.id.email_input)
        inGymValue = findViewById(R.id.tv_in_gim_value)
        inGymDecrement = findViewById(R.id.tv_in_gym_decrement)
        inGymIncrement = findViewById(R.id.tv_in_gym_increment)
        outdoorValue = findViewById(R.id.tv_outdoor_value)
        outdoorDecrement = findViewById(R.id.tv_outdoor_decrement)
        outdoorIncrement = findViewById(R.id.tv_outdoor_increment)
        healthyEatingValue = findViewById(R.id.tv_healthy_eating_value)
        healthyEatingDecrement = findViewById(R.id.tv_healthy_eating_decrement)
        healthyEatingIncrement = findViewById(R.id.tv_healthy_eating_increment)
        buttonSave = findViewById(R.id.b_save)
    }
    private fun fillingInResults(){
        //изменяем отображения кол-ва упражнений в зале
        inGymDecrement.setOnClickListener {
            writeValueInTextView(inGymValue, -1)
        }
        inGymIncrement.setOnClickListener {
            writeValueInTextView(inGymValue, 1)
        }
        //изменяем отображения кол-ва упражнений на улице
        outdoorDecrement.setOnClickListener {
            writeValueInTextView(outdoorValue, -1)
        }
        outdoorIncrement.setOnClickListener {
            writeValueInTextView(outdoorValue, 1)
        }
        //изменяем отображения кол-ва здорового питания
        healthyEatingDecrement.setOnClickListener {
            writeValueInTextView(healthyEatingValue, -1)
        }
        healthyEatingIncrement.setOnClickListener {
            writeValueInTextView(healthyEatingValue, 1)
        }
    }

    private fun writeValueInTextView(textView: TextView, difference: Int) { //TODO
        var result = textView.text.toString()
        var value = parseInt(result.get(0).toString())
        if (difference == -1 && value > 0) {
            textView.text = (value - 1).toString() + result.subSequence(1, result.length)
        } else if (difference == 1 && value<6) {
            textView.text = (value + 1).toString() + result.subSequence(1, result.length)
        }
    }

    private fun saveValueInRep() {
        //сохраняем данные в репозиторий
        repository.name = name.text.toString()
        repository.email = email.text.toString()
        repository.numberOfExampleInGym = parseInt(inGymValue.text.get(0).toString())
        repository.numberOfExampleOutdoor = parseInt(outdoorValue.text.get(0).toString())
        repository.numberOfHealthyEating = parseInt(healthyEatingValue.text.get(0).toString())
        repository.saveDataBase()
    }
    private fun loadValueFromRep() {
        //загружаем данные из репозитория
        name.setText(repository.name)
        email.setText(repository.email)
        inGymValue.text = repository.numberOfExampleInGym.toString()+"/6"
        outdoorValue.text = repository.numberOfExampleOutdoor.toString()+"/6"
        healthyEatingValue.text = repository.numberOfHealthyEating.toString()+"/6"
    }

}



