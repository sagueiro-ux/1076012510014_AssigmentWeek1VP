package com.seanagueiro.soal1

class System {
    private val menu = mutableListOf<Food>()
    private val orders = mutableListOf<Order>()
    private var nextFoodId = 1

    fun start() {
        initializeMenu()
        while (true) {
            displayMainMenu()
            when (readInt("Choose menu: ")) {
                1 -> makeOrder()
                2 -> viewOrders()
                3 -> viewMenu()
                4 -> addMenu()
                5 -> editMenu()
                6 -> deleteMenu()
                7 -> {
                    println()
                    println("Thank you!")
                    return
                }
                else -> {
                    println("Please choose 1-7.")
                }
            }
        }
    }

    private fun initializeMenu() {
        menu.add(
            Food(
                nextFoodId++,
                "Burger",
                "Beef burger with cheese",
                20.0
            )
        )
        menu.add(
            Food(
                nextFoodId++,
                "French Fries",
                "Crispy potato fries",
                10.0
            )
        )
        menu.add(
            Food(
                nextFoodId++,
                "Fried Chicken",
                "Crispy fried chicken",
                15.0
            )
        )
        menu.add(
            Food(
                nextFoodId++,
                "Fried Chicken Mozzarella",
                "Crispy fried chicken added with Mozzarella",
                17.0
            )
        )
    }
    private fun displayMainMenu() {
        println()
        println("===================================")
        println("         RESTAURANT SYSTEM         ")
        println("===================================")
        println("1. Make Order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        println("===================================")
    }

    private fun viewMenu() {
        println()
        println("=================== MENU =====================")

        if (menu.isEmpty()) {
            println("Menu is empty.")
            return
        }
        for (food in menu) {
            food.displayInfo()
        }
        println("----------------------------------")
    }

    private fun addMenu() {
        println()
        println("========== ADD MENU ==========")
        val name = readNonEmptyString("Food name: ")
        val description = readNonEmptyString("Description: ")
        val price = readPositiveDouble("Price: $")

        val food = Food(nextFoodId++, name, description, price)

        menu.add(food)

        println()
        println("Menu successfully added!")
        println("Food ID: ${food.id}")
    }

    private fun editMenu() {
        println()
        println("========== EDIT MENU ==========")

            if (menu.isEmpty()) {
                println("ERROR: Menu is empty.")
                return
            }

        viewMenu()

        val id = readInt("Enter food ID: ")
        val food = findFood(id)

            if (food == null) {
                println("ERROR: Food ID does not exist.")
                return
            }

        println()
        println("Editing ${food.name}")

        print("New name: ")
        val name = readLine() ?: ""
        if (name.isNotBlank()) {
            food.name = name
        }

        print("New description: ")
        val description = readLine() ?: ""
        if (description.isNotBlank()) {
            food.description = description
        }

        while (true) {
            print("New price: ")
            val input = readLine() ?: ""
                if (input.isBlank()) {
                    break
                }
            val price = input.toDoubleOrNull()
                if (price == null || price <= 0) {
                    println("ERROR: Price must be greater than 0.")
                } else {
                    // Mengubah properti price langsung
                    food.price = price
                    break
                }
        }
        println("Menu updated!")
    }

    private fun deleteMenu() {
        println()
        println("========== DELETE MENU ==========")
            if (menu.isEmpty()) {
                println("ERROR: Menu is empty.")
                return
            }
        viewMenu()

        val id = readInt("Enter food ID: ")
        val food = findFood(id)
            if (food == null) {
                println("ERROR: Food ID does not exist.")
                return
            }
        print("Are you sure you want to delete ${food.name}? (Y/N): ")

        val confirmation = readLine()?.uppercase()
            if (confirmation == "Y") {
                menu.remove(food)
                println("Menu successfully deleted!")
            } else if (confirmation == "N") {
                println("Delete cancelled.")
            } else {
                println("ERROR: Please enter Y or N.")
            }
    }

    private fun makeOrder() {
        println()
        println("========== MAKE ORDER ==========")
            if (menu.isEmpty()) {
                println("ERROR: Cannot make order because menu is empty.")
                return
            }

        val customerName = readNonEmptyString("Customer name: ")
        val order = Order(customerName)

        while (true) {
            println()
            println("=========== MENU ===========")
                for (food in menu) {
                    println("${food.id}. " + "${food.name} - " + "$${"%.2f".format(food.price)}")
                }
            println("0. Finish Order")
            println("----------------------------")

            val id = readInt("Choose food ID: ")
                if (id == 0) {
                    break
                }

            val food = findFood(id)
                if (food == null) {
                    println("ERROR: Food ID does not exist.")
                    continue
                }

            val quantity = readPositiveInt("Quantity for ${food.name}: ")

            val existingItem = order.items.find {
                it.food.id == food.id
            }

                if (existingItem != null) {
                    existingItem.addQuantity(quantity)
                } else {
                    order.addItem(OrderItem(food, quantity))
                }

            println("${food.name} x$quantity added!")
        }

        if (order.items.isEmpty()) {
            println("ERROR: No food selected.")
            return
        }
        orders.add(order)

        println()
        println("Order successfully created!")

        order.displayOrder()
    }

    private fun viewOrders() {
        println()
        println("=========== ALL ORDERS ===========")

            if (orders.isEmpty()) {
                println("No orders have been made yet.")
                return
            }

            for ((index, order) in orders.withIndex()) {
                println()
                println("ORDER #${index + 1}")

                order.displayOrder()
            }
    }

    private fun findFood(id: Int): Food? {
        // Mengakses it.id langsung
        return menu.find {
            it.id == id
        }
    }

    private fun readInt(message: String): Int {
        while (true) {
            print(message)
            val input = readLine()
            try {
                return input!!.toInt()
            } catch (e: Exception) {
                println("ERROR: Please enter a valid number.")
            }
        }
    }

    private fun readPositiveInt(message: String): Int {
        while (true) {
            val number = readInt(message)
            if (number > 0) {
                return number
            }
            println("ERROR: Number must be greater than 0.")
        }
    }

    private fun readPositiveDouble(message: String): Double {
        while (true) {
            print(message)
            val input = readLine()
            try {
                val number = input!!.toDouble()
                if (number > 0) {
                    return number
                }
                println("ERROR: Price must be greater than 0.")
            } catch (e: Exception) {
                println("ERROR: Please enter a valid price.")
            }
        }
    }

    private fun readNonEmptyString(message: String): String {
        while (true) {
            print(message)
            val input = readLine()?.trim()
            if (!input.isNullOrEmpty()) {
                return input
            }
            println("ERROR: This field cannot be empty.")
        }
    }
}