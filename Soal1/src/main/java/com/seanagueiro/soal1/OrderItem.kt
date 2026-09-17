package com.seanagueiro.soal1

class OrderItem(
    val food: Food,
    var quantity: Int
) {

    fun addQuantity(amount: Int) {
        quantity += amount
    }

    fun getSubtotal(): Double {
        return food.price * quantity
    }

    fun displayOrderItem(number: Int) {
        println(
            "$number. ${food.name} x$quantity " + "$${"%.2f".format(getSubtotal())}")
    }
}