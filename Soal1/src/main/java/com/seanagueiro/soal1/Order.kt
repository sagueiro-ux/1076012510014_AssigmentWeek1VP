package com.seanagueiro.soal1

class Order(
    val customerName: String
) {
    val items = mutableListOf<OrderItem>()
    fun addItem(item: OrderItem) {
        items.add(item)
    }

    fun calculateTotal(): Double {
        return items.sumOf {
            it.getSubtotal()
        }
    }

    fun displayOrder() {
        println()
        println("==== $customerName's ORDER ====")

            if (items.isEmpty()) {
                println("No items.")
                return
            }

            for ((index, item) in items.withIndex()) {
                item.displayOrderItem(index + 1)
            }

        println("===============================")
        println("TOTAL: $${"%.2f".format(calculateTotal())}")
    }
}