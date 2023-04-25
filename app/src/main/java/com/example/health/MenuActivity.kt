package com.example.health

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class MenuActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var buttonProfile: Button
    val ARG_EXERXISE = "argExercise"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
        //переделали категории на список типа recicler view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //добавляем слушателя на нажатие по элементу
        val onItemClickListener = object : OnItemClickListener {
            override fun invoke(name: String) {
                openListOfExercise (name)
            }
        }
        val adapter = MenuAdapter(this, onItemClickListener)
        recyclerView.adapter = adapter
    }

    private fun init() {
        recyclerView = findViewById(R.id.rv_category_list)
        buttonProfile = findViewById(R.id.b_profile)
    }
    private fun openListOfExercise(name: String){
        //запускаем следующую активити
        val intent = Intent(this, ExerciseActivity::class.java)
            //передаем в будущую активити значение имени элемента
        intent.putExtra(ARG_EXERXISE, name)
        startActivity(intent)
    }
}