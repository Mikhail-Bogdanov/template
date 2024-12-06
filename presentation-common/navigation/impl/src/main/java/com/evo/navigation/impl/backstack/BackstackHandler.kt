package com.evo.navigation.impl.backstack

import com.evo.navigation.api.Screen

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