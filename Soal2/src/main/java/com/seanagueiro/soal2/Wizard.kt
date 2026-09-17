package com.seanagueiro.soal2

class Wizard(var name: String) {
    var maxHp: Int = 50
    var currentHp: Int = 50
    var maxMana: Int = 30
    var currentMana: Int = 30
    var killsCount: Int = 0
    val killsNeededToEvolve: Int = 5
    var isEvolved: Boolean = false
    var healthPotions: Int = 5
    var manaPotions: Int = 5
    var baseDamage: Int = 10
    var lifesteal: Int = 0

    fun addKill() {
        killsCount++
            if (isEvolved) {
                lifesteal++
                println("Lifesteal increased to $lifesteal!")
            } else if (killsCount >= killsNeededToEvolve) {
                evolve()
            }
    }
    private fun evolve() {
        isEvolved = true
        lifesteal = 1

        maxHp = (maxHp * 1.5).toInt()
        currentHp = maxHp
        maxMana = (maxMana * 1.5).toInt()
        currentMana = maxMana
        baseDamage = (baseDamage * 1.5).toInt()

        println("\n You are evolved become Strong Wizard! ")
        println("Stats 1.5x increased & Lifesteal unlocked!")
    }

    fun resetStats() {
        maxHp = 50
        currentHp = 50
        maxMana = 30
        currentMana = 30
        killsCount = 0
        isEvolved = false
        healthPotions = 5
        manaPotions = 5
        baseDamage = 10
        lifesteal = 0
    }
}