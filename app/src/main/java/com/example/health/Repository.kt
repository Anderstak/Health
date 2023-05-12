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
    val examplesAtHome = ArrayList<ExampleItem>()
    val examplesOutdoor = ArrayList<ExampleItem>()
    val healthyEating = ArrayList<ExampleItem>()

    var menuCategory: List<String> = ArrayList()


    fun saveDataBase() {
        //если крашится "В спортазале" разкоментировать и сохранить через профиль
        clear()
        addData()
        //изменение элемента в репозитории
//        reData()


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

    private fun addData() {
        val exampleZero = ExampleItem(
            "Тяга блока",
            "В упражнении участвует практически весь верх тела. При технически правильной работе " +
                    "значительную нагрузку берут на себя широчайшие мышцы спины.В упражнении участвует " +
                    "практически весь верх тела. При технически правильной работе значительную нагрузку " +
                    "берут на себя широчайшие мышцы спины. \\nВ качестве вспомогательных мышц " +
                    "активируются:\\nбольшая круглая мышца спины\\n бицепсы\\nпредплечье\\nтрапециевидная мышца\\n"
        )
        exampleZero.imageOne = R.drawable.img_block_thrust_one
        exampleZero.imageTwo = R.drawable.img_block_thrust_two
        exampleZero.imageThree = R.drawable.img_block_thrust_three
        examplesInGym.add(exampleZero)

        val exampleOne = ExampleItem(
            "Подтягивание",
            "Задействованы сразу несколько групп мышц спины, груди, живота, плечевого пояса"
        )
        exampleOne.imageOne = R.drawable.img_pull_up_one
        exampleOne.imageTwo = R.drawable.img_pull_up_two
        exampleOne.imageThree = R.drawable.img_pull_up_three
        examplesInGym.add(exampleOne)

        val exampleTwo = ExampleItem(
            "Сгибание ног",
            "Основными работающими мышцами являются:\\nмышцы задней поверхности бедра\\nОсновными " +
                    "работающими мышцами являются:\\nмышцы задней поверхности бедра\\n"
        )
        exampleTwo.imageOne = R.drawable.leg_curel_one
        exampleTwo.imageTwo = R.drawable.leg_curel_two
        examplesInGym.add(exampleTwo)

        val exampleThree = ExampleItem(
            "Жим ногами",
            "В упражнении задействованы все ягодичные мышцы и мышцы ног:\\nгрушевидная мышца\\nквадратная" +
                    " мышца бедра\\nбицепс бедра \\nчетырехглавая мышца бедра\\n приводящие мышцы бедра\\n"
        )
        exampleThree.imageOne = R.drawable.leg_press_one
        exampleThree.imageTwo = R.drawable.leg_press_two
        exampleThree.imageThree = R.drawable.leg_press_three
        examplesInGym.add(exampleThree)

        val exampleFour = ExampleItem(
            "Гиперэкстензия",
            "Гиперэкстензия — это изолирующее упражнение для развития мышц спины, ягодиц, коленных " +
                    "сгибателей. Так же позволяет укрепить мышцы позвоночного столба"
        )
        exampleFour.imageOne = R.drawable.hyperextension_one
        exampleFour.imageTwo = R.drawable.hyperextension_two
        examplesInGym.add(exampleFour)

        val exampleFive = ExampleItem(
            "Гиперэкстензия",
            "Гиперэкстензия — это изолирующее упражнение для развития мышц спины, ягодиц, коленных " +
                    "сгибателей. Так же позволяет укрепить мышцы позвоночного столба"
        )
        exampleFour.imageOne = R.drawable.hyperextension_one
        exampleFour.imageTwo = R.drawable.hyperextension_two
        examplesInGym.add(exampleFour)


        //ДОМА
        val exampleSix = ExampleItem(
            "Отжимания",
            "Основную часть нагрузки при выполнении отжимания берут на себя грудные мышцы и трицепсы.\n" +
                    "Внешняя части грудных мышц\n" +
                    "Медиальный пучок трицепса\n" +
                    "Ягодичные мышцы\n" +
                    "Мышцы пресса и поясницы\n"
        )
        exampleSix.imageOne = R.drawable.push_up1
        exampleSix.imageTwo = R.drawable.push_up_half
        exampleSix.imageThree = R.drawable.push_up2
        examplesAtHome.add(exampleSix)

        val exampleSeven = ExampleItem(
            "Разведение рук",
            "Разводка рук стоя тренирует среднюю дельту, расстягивает грудную клетку\\n"
        )
        exampleSeven.imageOne = R.drawable.arm_stretching1
        exampleSeven.imageTwo = R.drawable.arm_stretching_half
        exampleSeven.imageThree = R.drawable.arm_stretching2
        examplesAtHome.add(exampleSeven)

        val exampleEight = ExampleItem(
            "Планка",
            "Планка – универсальное упражнение, которое задействует множество групп мышц во время выполнения, такие как мышцы рук, пресса, спины , ног и бедер , шеи" +
                      "Осанка становится прямее и ровнее, а воздействие на мышцы пресса делает живот красивым и подтянутым\\n"
        )
        exampleEight.imageOne = R.drawable.plank_main
        exampleEight.imageTwo = R.drawable.plank_left
        exampleEight.imageThree = R.drawable.plunk_right
        examplesAtHome.add(exampleEight)

        val exampleNine = ExampleItem(
            "Пресс",
            "Пресс образуют четыре мышечные группы: прямая и поперечная мышцы живота, внешняя косая и внутренняя косая мышцы. Поэтому получить проработанный пресс можно только комплексом упражнений - нагружая каждую мышцу живота отдельно и в комбинации с другими\\n"
        )
        exampleNine.imageOne = R.drawable.press1
        exampleNine.imageTwo = R.drawable.press3
      //  exampleThree.imageThree = R.drawable.press2
        examplesAtHome.add(exampleNine)


        val exampleTen = ExampleItem(
            "Расслабление спины на коленях",
            "Исходное положение: упор на колени и ладони.\n" +
                    "1.\tНа выдохе плавно погнуть спину вверх\n" +
                    "2.\tВернуться в исх. Положение\n" +
                    "3.\tНа выдохе плавно прогнуть спину вниз\n" +
                    "4.\tВернуться в исх. Положение\n" +
                    "5.\tКоличество повторений: 20 раз.\n"
        )
        exampleTen.imageOne = R.drawable.relaxation_of_the_back_on_the_knees1
        exampleTen.imageTwo = R.drawable.relaxation_of_the_back_on_the_knees2
        exampleTen.imageThree = R.drawable.relaxation_of_the_back_on_the_knees3
        examplesAtHome.add(exampleTen)

        val exampleEleven = ExampleItem(
            "Подъем таза",
            "Исходное Положение: лежа на спине, ноги согнуты в коленных суставах, пятки на полу, руки вдоль тела.\n" +
                    "На выдохе оторвать таз от пола как можно выше и также на выдохе врнуться  исх.положене.\n"
        )
        exampleEleven.imageOne = R.drawable.pelvic_lift1
        exampleEleven.imageTwo = R.drawable.pelvic_lift2
       // exampleThree.imageThree = R.drawable.relaxation_of_the_back_on_the_knees3
        examplesAtHome.add(exampleEleven)
    }




    private fun reData() {
        val example = examplesInGym.get(2)
        example.imageOne = R.drawable.img_pull_up_outdoor_one
        example.imageTwo = R.drawable.img_pull_up_outdoor_two
        example.imageThree = R.drawable.img_white_box
        examplesInGym.removeAt(2)
        examplesInGym.add(2, example)
    }

    private fun clear() {
        examplesInGym.removeAll(examplesInGym)
        examplesOutdoor.removeAll(examplesOutdoor)
        healthyEating.removeAll(healthyEating)
    }


}

