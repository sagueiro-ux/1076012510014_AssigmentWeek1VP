package com.seanagueiro.soal2

class GameSystem {
    private lateinit var wizard: Wizard
    private val healthPotion = Potion("Health", 25)
    private val manaPotion = Potion("Mana", 15)

    fun start() {
        print("What's your name?\n> ")
        var inputName = readLine() ?: ""
            while (inputName.isBlank()) {
                print("Name cannot be empty. Try again:\n> ")
                inputName = readLine() ?: ""
            }

        wizard = Wizard(inputName)
        println("\nGood luck, ${wizard.name}! You're gonna need it!\n")

        mainMenu()
    }

    private fun mainMenu() {
        while (true) {
            println("\nWhat're you going to do?")
            println("1. View Stats")
            println("2. Enter battle")
            print("Choose: ")

            val choice = readLine() ?: ""
            when (choice) {
                "1" -> viewStats()
                "2" -> {
                    val monster = Monster()
                    val battle = Duel(wizard, monster)
                    battle.startBattle()
                }
                "3" -> println("See you next time!")
                else -> println("Invalid Input!")
            }
        }
    }

    private fun viewStats() {
        while (true) {
            println("\n----${wizard.name}'s STATS ----")
            println("HP: ${wizard.currentHp}/ ${wizard.maxHp}")
            println("Mana: ${wizard.currentMana}/ ${wizard.maxMana}")
                if (!wizard.isEvolved) {
                    println("Kills needed to evolve: ${wizard.killsCount}/ ${wizard.killsNeededToEvolve}")
                } else {
                    println("Status: EVOLVED")
                    println("Lifesteal: ${wizard.lifesteal}")
                }
            println("Mana Potions held: ${wizard.manaPotions}")
            println("Health Potions held: ${wizard.healthPotions}")
            println("------------------")
            println("a. Drink Mana Potion")
            println("b. Drink Health Potion")
            println("c. Rename self")
            println("d. Back")
            print("Choose: ")

            val choice = readLine() ?: ""
            when (choice.lowercase()) {
                "a" -> {
                    if (manaPotion.use(wizard)) println("Drink Mana Potion!")
                    else println("Out of Mana Potions!")
                }
                "b" -> {
                    if (healthPotion.use(wizard))
                        println("Drink Health Potion!")
                    else println("Out of Health Potions!")
                }
                "c" -> {
                    print("Enter new name: ")
                    val newName = readLine() ?: ""
                    if (newName.isNotBlank()) {
                        wizard.name = newName
                        println("Name changed!")
                    }
                }
                "d" -> return
                else -> println("Invalid Input, Try Again")
            }
        }
    }
}