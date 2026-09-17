package com.seanagueiro.soal2

class Potion(val type: String, val restoreAmount: Int) {

    fun use(wizard: Wizard): Boolean {
        if (type == "Health") {
            if (wizard.healthPotions <= 0) return false
                wizard.healthPotions--
                wizard.currentHp += restoreAmount
            if (wizard.currentHp > wizard.maxHp) {
                wizard.currentHp = wizard.maxHp
            }
            return true
        } else if (type == "Mana") {
            if (wizard.manaPotions <= 0) return false
                wizard.manaPotions--
                wizard.currentMana += restoreAmount
            if (wizard.currentMana > wizard.maxMana) {
                wizard.currentMana = wizard.maxMana
            }
            return true
        }
        return false
    }
}