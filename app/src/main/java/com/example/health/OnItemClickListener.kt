package com.example.health

//интерфейс слушателя с дополнительным параметрои - именем
interface OnItemClickListener {
    fun invoke(name: String)
}