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
//        addData(healthyEating)
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
        var list: Arraylist<Examples> =  examplesInGym
        val example = ExampleItem("textHeadline", "textValue")
        example.imageOne=R.drawable.img_kartinka
        example.imageTwo=R.drawable.img_kartinka
        example.imageThree=R.drawable.img_kartinka
        examples.add(example)
    }


}

