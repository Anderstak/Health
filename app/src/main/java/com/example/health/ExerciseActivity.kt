package com.example.health

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ExerciseActivity : AppCompatActivity() {
    lateinit var repository: Repository
    lateinit var buttonBack: ImageView
    lateinit var headliner: TextView
    lateinit var imageOne: ImageView
    lateinit var imageTwo: ImageView
    lateinit var imageThree: ImageView
    lateinit var text: TextView
    lateinit var buttonNext: Button
    lateinit var examples: List<ExampleItem>
    val ARG_EXERXISE = "argExercise"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        init()
        //Получаем из интента имя группы упражнений
        val nameExample: String? = intent.getStringExtra(ARG_EXERXISE)
        //загружаем из репозитория нужную группу
        if (nameExample.equals("В спортзале")) {
            examples = repository.examplesInGym
            createActivityData(examples, 0)
        } else if (nameExample.equals("Дома")) {
            examples = repository.examplesOutdoor
            createActivityData(examples, 0)
        } else if (nameExample.equals("Здоровое питание")) {
            examples = repository.healthyEating
            createActivityData(examples, 0)
        } else {
            Toast.makeText(this, "Ошибка загрузки", Toast.LENGTH_SHORT).show()
        }
        // слушатель на кнопку назад
        buttonBack.setOnClickListener {
            backScreen()
        }
        //слушатель на кнопку следующий
        buttonNext.setOnClickListener {
            nextScreen()
        }

    }

    private fun init() {
        repository = Repository.getInstance()
        buttonBack = findViewById(R.id.iv_icon_back)
        headliner = findViewById(R.id.tv_headline)
        imageOne = findViewById(R.id.iv_exercise_one)
        imageTwo = findViewById(R.id.iv_exercise_two)
        imageThree = findViewById(R.id.iv_exercise_three)
        text = findViewById(R.id.tv_text_exercise)
        buttonNext = findViewById(R.id.b_profile)
    }

    private fun createActivityData(examples: List<ExampleItem>, number: Int) {
        //выбираем нужный итем
        var exampleItem = examples.get(number)
        //устанавливаем во вью нужные данные
        headliner.text = exampleItem.name
        imageOne.setImageDrawable(ContextCompat.getDrawable(this, exampleItem.imageOne))
        imageTwo.setImageDrawable(ContextCompat.getDrawable(this, exampleItem.imageTwo))
        imageThree.setImageDrawable(ContextCompat.getDrawable(this, exampleItem.imageThree))
        text.text = exampleItem.value
        //если элемент последний - скрываем кнопку "следуйщее"
        if (examples.size == (number + 1)) {
            buttonNext.visibility = View.GONE
        } else {
            buttonNext.visibility = View.VISIBLE
        }
    }

    private fun backScreen() {
        //получаем текущий заголовок
        var name = headliner.text.toString()
        //ищем со второго элемента до конца списка заголовок
        for (i in 1 until examples.size) {
            if (name.equals(examples.get(i).name)) {
                //обновляем данные на экране
                createActivityData(examples, i - 1)
                //останавливаем цикл
                break
            }
            //если нету прдыдущих упражнений - открывает menuActivity
            onBackPressed()
        }
    }

    private fun nextScreen() {
        //получаем текущий заголовок
        var name = headliner.text.toString()
        //ищем с первого элемента до предпоследнего в списке заголовок
        for (i in 0 until (examples.size - 1)) {
            if (name.equals(examples.get(i).name)) {
                //обновляем данные на экране
                createActivityData(examples, i + 1)
                //останавливаем цикл
                break
            }
        }
    }
}


