package com.seanagueiro.soal1

abstract class MenuItem(
    val id: Int,
    var name: String,
    var description: String,
    var price: Double
) {
    abstract fun displayInfo()
}