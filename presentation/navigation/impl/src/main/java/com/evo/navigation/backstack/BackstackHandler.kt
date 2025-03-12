package com.evo.navigation.backstack

import com.evo.navigation.Screen

interface BackstackHandler {
    fun put(screen: Screen)

    /**
     * @throws IllegalArgumentException if backstack size < [quantity]
     *
     */
    fun replaceScreens(screen: Screen, quantity: Int)

    fun replaceAll(screen: Screen)

    /**
     * @throws IllegalArgumentException if backstack size <= [quantity]
     *
     */
    fun removeScreens(quantity: Int)

    fun removeUntilFirst()
}