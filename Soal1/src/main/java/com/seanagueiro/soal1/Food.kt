package com.seanagueiro.soal1

class Food(
    id: Int,
    name: String,
    description: String,
    price: Double
) : MenuItem(id, name, description, price) {

    override fun displayInfo() {
        println("==============================================")
        println("ID          : $id")
        println("Name        : $name")
        println("Description : $description")
        println("Price       : $${"%.2f".format(price)}")
    }
}