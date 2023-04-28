package com.example.health

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

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

    var userName = "login"
    var password = "password"
    var name = ""
    var email = ""
    var numberOfExampleInGym = 0
    var numberOfExampleOutdoor = 0
    var numberOfHealthyEating = 0
    val examplesInGym = ArrayList<ExampleItem>().apply {
        add(
            ExampleItem(
                "Тяга блока", "В упражнении участвует практически весь " +
                        "верх тела. При технически правильной работе значительную нагрузку берут на себя " +
                        "широчайшие мышцы спины.В упражнении участвует практически весь верх тела. При " +
                        "технически правильной работе значительную нагрузку берут на себя широчайшие мышцы спины." +
                        " \nВ качестве вспомогательных мышц активируются:\nбольшая круглая мышца спины\n" +
                        " бицепсы\nпредплечье\nтрапециевидная мышца\n"
            ).apply {
                imageOne = (R.drawable.img_block_thrust_one)
                imageTwo = (R.drawable.img_block_thrust_two)
                imageThree = (R.drawable.img_block_thrust_three)
            }
        )
        add(
            ExampleItem(
                "Подтягивание",
                "Задействованы сразу несколько групп мышц спины, груди, живота, плечевого пояса.\n"
            ).apply {
                imageOne = (R.drawable.img_pull_up_one)
                imageTwo = (R.drawable.img_pull_up_two)
                imageThree = (R.drawable.img_pull_up_three)
            }
        )
    }
    val examplesOutdoor = ArrayList<ExampleItem>().apply {
        add(
            ExampleItem(
                "Подтягивание",
                "Задействованы сразу несколько групп мышц спины, груди, живота, плечевого пояса.\n"
            ).apply {
                imageOne = (R.drawable.img_pull_up_outdoor_one)
                imageTwo = (R.drawable.img_pull_up_outdoor_two)
                imageThree = (R.drawable.img_pull_up_three)
            }
        )
    }
    val healthyEating = ArrayList<ExampleItem>().apply {
        add(
            ExampleItem(
                "Подтягивание",
                "Задействованы сразу несколько групп мышц спины, груди, живота, плечевого пояса.\n"
            ).apply {
                imageOne = (R.drawable.img_pull_up_outdoor_one)
                imageTwo = (R.drawable.img_pull_up_outdoor_two)
                imageThree = (R.drawable.img_pull_up_three)
            }
        )
    }

    var menuCategory: List<String> = ArrayList(listOf("В спортзале", "Дома", "Здоровое питание"))


    fun saveDataBase() {
        val database = Firebase.database
        val myRef = database.getReference("repository")
        var result =
            "" + userName + ";" + password + ";" + name + ";" + email + ";" + numberOfExampleInGym + ";" + numberOfExampleOutdoor + ";" + numberOfHealthyEating + "|"
        var resultExamplesInGym = ""
        for (i in 0 until examplesInGym.size) {
            resultExamplesInGym += examplesInGym[i].name + ";" + examplesInGym[i].imageOne + ";" + examplesInGym[i].imageTwo + ";" + examplesInGym[i].imageThree + ";"
        }
        var resultExamplesOutdoor = ""
        for (i in 0 until examplesOutdoor.size) {
            resultExamplesOutdoor += examplesOutdoor[i].name + ";" + examplesOutdoor[i].imageOne + ";" + examplesOutdoor[i].imageTwo + ";" + examplesOutdoor[i].imageThree + ";"
        }
        var resultHealthyEating = ""
        for (i in 0 until healthyEating.size) {
            resultHealthyEating += healthyEating[i].name + ";" + healthyEating[i].imageOne + ";" + healthyEating[i].imageTwo + ";" + healthyEating[i].imageThree + ";"
        }
        var resultMenuCategory = ""
        for (i in 0 until menuCategory.size) {
            resultMenuCategory += menuCategory[i] + ";"
        }
        result += resultExamplesInGym + "|" + resultExamplesOutdoor + "|" + resultHealthyEating + "|" + menuCategory

        myRef.setValue(result)
    }

    private fun loadDataBase() {
        val database = Firebase.database
        val myRef = database.getReference("repository")
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                writeInRepository(snapshot.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun writeInRepository(result: String) {
        var arrayResult = result.split("|")
        var arrayVariables = arrayResult[0].split(";")
        userName = arrayVariables[0]
        password = arrayVariables[1]
        name = arrayVariables[2]
        email = arrayVariables[3]
        numberOfExampleInGym = arrayVariables[4].toInt()
        numberOfExampleOutdoor = arrayVariables[5].toInt()
        numberOfHealthyEating = arrayVariables[6].toInt()
        var arrayExamplesInGym = arrayResult[1].split(";")
        for (i in 0 until arrayExamplesInGym.size step 5) {
            if (i + 4 >= arrayExamplesInGym.size) {
                break
            }
            val exampleItem = ExampleItem(arrayExamplesInGym[i], arrayExamplesInGym[i + 1]).apply {
                imageOne = arrayExamplesInGym[i + 2].toInt()
                imageTwo = arrayExamplesInGym[i + 3].toInt()
                imageThree = arrayExamplesInGym[i + 4].toInt()
            }
            examplesInGym.add(exampleItem)
        }
        var arrayExamplesOutdoor = arrayResult[2].split(";")
        for (i in 0 until arrayExamplesOutdoor.size step 5) {
            if (i + 4 >= arrayExamplesOutdoor.size) {
                break
            }
            val exampleItem =
                ExampleItem(arrayExamplesOutdoor[i], arrayExamplesOutdoor[i + 1]).apply {
                    imageOne = arrayExamplesOutdoor[i + 2].toInt()
                    imageTwo = arrayExamplesOutdoor[i + 3].toInt()
                    imageThree = arrayExamplesOutdoor[i + 4].toInt()
                }
            examplesOutdoor.add(exampleItem)
        }
        var arrayHealthyEating = arrayResult[3].split(";")
        for (i in 0 until arrayHealthyEating.size step 5) {
            if (i + 4 >= arrayHealthyEating.size) {
                break
            }
            val exampleItem = ExampleItem(arrayHealthyEating[i], arrayHealthyEating[i + 1]).apply {
                imageOne = arrayHealthyEating[i + 2].toInt()
                imageTwo = arrayHealthyEating[i + 3].toInt()
                imageThree = arrayHealthyEating[i + 4].toInt()
            }
            healthyEating.add(exampleItem)
        }
        var arrayMenu = arrayResult[4].split(";")
        menuCategory = ArrayList(arrayMenu)

    }
}

