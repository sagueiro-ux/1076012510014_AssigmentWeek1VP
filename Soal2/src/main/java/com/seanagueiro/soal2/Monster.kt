package com.seanagueiro.soal2

import kotlin.random.Random

class Monster(val element: Element) {
    val name: String = element.name + "mon"
    var currentHp: Int = 30
    val maxHp: Int = 30
    val attackDamage: Int = 10

    //Biar langsung bisa acak monster yang lawan nantinya
    constructor() : this(
        listOf(Element("Fire"), Element("Water"), Element("Grass"))[Random.nextInt(3)]
    )
}