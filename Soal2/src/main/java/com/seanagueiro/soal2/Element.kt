package com.seanagueiro.soal2
import kotlin.random.Random

class Element(val name: String) {

    fun getMultiplier(targetElement: Element): Int {
        if (this.name == "Fire" && targetElement.name == "Grass") return 2
        if (this.name == "Water" && targetElement.name == "Fire") return 2
        if (this.name == "Grass" && targetElement.name == "Water") return 2
        return 1
    }

    fun getRandomElement(): Element {
        val elements = listOf(Element("Fire"), Element("Water"), Element("Grass"))
        val randomIndex = Random.nextInt(elements.size)
        return elements[randomIndex]
    }
}