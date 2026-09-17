package com.seanagueiro.soal2

class Duel(val wizard: Wizard, val monster: Monster) {
    val healthPotion = Potion("Health", 25)
    val manaPotion = Potion("Mana", 15)

    fun startBattle(): Boolean {
        println("\nA wild ${monster.name} appeared!")

        while (wizard.currentHp > 0 && monster.currentHp > 0) {
            displayBattleStatus()

            println("a. Fire Attack")
            println("b. Water Attack")
            println("c. Grass Attack")
            println("d. Drink potion")
            println("e. Run")
            print("Choose: ")

            val input = readLine() ?: ""
            when (input.lowercase()) {
                "a" -> attackWithElement(Element("FIRE"))
                "b" -> attackWithElement(Element("WATER"))
                "c" -> attackWithElement(Element("GRASS"))
                "d" -> handlePotion()
                "e" -> {
                    println("You ran away!")
                    return true
                }
                else -> {
                    println("Invalid choice!")
                    continue
                }
            }

                if (monster.currentHp <= 0) {
                    println("\n You defeated ${monster.name}!")
                    wizard.addKill()
                    return true
                }

            println("${monster.name} attacks for ${monster.attackDamage} damage!")
            wizard.currentHp -= monster.attackDamage

                if (wizard.currentHp <= 0) {
                    println("\nYOU DIED! Restarting game from tower...\n")
                    wizard.resetStats()
                    return false
                }
        }
        return true
    }

    private fun displayBattleStatus() {
        println("\n--- BATTLE ---")
        println("HP: ${wizard.currentHp}/ ${wizard.maxHp}")
        println("Mana: ${wizard.currentMana}/ ${wizard.maxMana}")
        println("HP Potions: ${wizard.healthPotions}")
        println("MP Potions: ${wizard.manaPotions}")
        println("----------------")
        println("${monster.name}")
        println("HP: ${monster.currentHp}/ ${monster.maxHp}")
        println("Type: ${monster.element.name}")
        println("----------------")
    }

    private fun attackWithElement(spellElement: Element) {
        if (wizard.currentMana < 10) {
            println("Not enough Mana! (Need 10 Mana)")
            return
        }

        wizard.currentMana -= 10
        val multiplier = spellElement.getMultiplier(monster.element)
        val totalDamage = wizard.baseDamage * multiplier

        monster.currentHp -= totalDamage
        if (monster.currentHp < 0) monster.currentHp = 0

        println("You used ${spellElement.name} attack and dealt $totalDamage damage!")

        //KALO EVOLVED
        if (wizard.isEvolved && wizard.lifesteal > 0) {
            wizard.currentHp += wizard.lifesteal
            if (wizard.currentHp > wizard.maxHp) {
                wizard.currentHp = wizard.maxHp
            }
            println("Lifesteal recovered ${wizard.lifesteal} HP!")
        }
    }

    private fun handlePotion() {
        print("1. Health Potion\n2. Mana Potion\nChoose: ")
        val choice = readLine() ?: ""
            if (choice == "1") {
                if (healthPotion.use(wizard)) println("Drank Health Potion!")
                else println("Out of Health Potions!")
            } else if (choice == "2") {
                if (manaPotion.use(wizard)) println("Drank Mana Potion!")
                else println("Out of Mana Potions!")
            }
    }
}