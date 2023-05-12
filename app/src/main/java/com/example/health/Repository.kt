package com.example.health

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*


class Repository private constructor() {
    companion object {
        private var repository: Repository? = null
        fun getInstance(): Repository {
            if (repository == null) {
                repository = Repository()
                repository!!.loadDataBase()
            }
            return repository!!
        }
    }

    var userName = ""
    var password = ""
    var name = ""
    var email = ""
    var numberOfExampleInGym = 0
    var numberOfExampleOutdoor = 0
    var numberOfHealthyEating = 0
    val examplesInGym = ArrayList<ExampleItem>()
    val examplesOutdoor = ArrayList<ExampleItem>()
    val healthyEating = ArrayList<ExampleItem>()

    var menuCategory: List<String> = ArrayList()


    fun saveDataBase() {
        //просто загрука в бд данных
//        addData()
//        reData()
//        clear()

        //ищем на сервере Firebase нужную бд по имени
        val reference = Firebase.database.getReference("repository")
        // преобразовываем класс репозитория в json
        var repositoryToJson = Gson().toJson(repository)
        //перезаписываем в бд json
        reference.setValue(repositoryToJson)
    }

    private fun loadDataBase() {
        //ищем на сервере Firebase нужную бд по имени
        val reference = Firebase.database.getReference("repository")
        //отправляем запрос к бд на получение всех данных
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //создаем объект gson для использования методов преобразования входящих в него
                val gson: Gson = GsonBuilder().create()
                //преобразовываем json в объект repository, сохраняем
                repository = gson.fromJson(snapshot.value.toString(), Repository::class.java)
            }

            //здесь может быть описана обработка ошибок при загрузке из бд, ничего не пишем
            override fun onCancelled(error: DatabaseError) {
            }

        })
        //костыль, ожидание загрузки репозитория, подумать - доработать для автоматической загрузки
        Thread.sleep(1000)
    }

    private fun addData(){
        val exampleZero = ExampleItem("Жим ногами", "В упражнении задействованы все ягодичные мышцы и мышцы ног:\nгрушевидная мышца\n" +
                "квадратная мышца бедра\n" +
                "бицепс бедра \n" +
                "четырехглавая мышца бедра\n" +
                " приводящие мышцы бедра\n")
        exampleZero.imageOne=R.drawable.leg_press_one
        exampleZero.imageTwo=R.drawable.leg_press_two
        exampleZero.imageThree=R.drawable.leg_press_three
        examplesInGym.add(exampleZero)
    }
    private fun reData(){
        val example = examplesInGym.get(2)
//        example.imageOne=R.drawable.img_pull_up_outdoor_one
//        example.imageTwo=R.drawable.img_pull_up_outdoor_two
        example.imageThree=R.drawable.img_white_box
        examplesInGym.removeAt(2)
        examplesInGym.add(2, example)
    }
    private fun clear(){
        examplesOutdoor.removeAll(examplesOutdoor)
        healthyEating.removeAll(healthyEating)
    }


}

