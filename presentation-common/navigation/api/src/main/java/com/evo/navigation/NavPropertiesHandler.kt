package com.evo.navigation

interface NavPropertiesHandler {

    fun replaceAll()

    /**
     * @throws IllegalStateException if [quantity] < 0
     */
    fun replace(quantity: Int)
}

interface PopPropertiesHandler {

    fun popAll()

    /**
     * @throws IllegalStateException if [quantity] <= 0
     */
    fun pop(quantity: Int)
}