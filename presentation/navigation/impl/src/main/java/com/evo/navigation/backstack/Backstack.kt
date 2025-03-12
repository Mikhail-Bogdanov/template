package com.evo.navigation.backstack

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.evo.navigation.Screen
import java.util.Stack


class Backstack(
    initialScreen: Screen,
) : BackstackHandler {

    private val backstackValue: Stack<Screen> = Stack<Screen>().apply {
        addElement(initialScreen)
    }

    var backstackState by mutableStateOf(backstackValue.toList())
        private set

    override fun put(screen: Screen) {
        backstackValue.addElement(screen)
        resetValue()
    }

    override fun replaceScreens(screen: Screen, quantity: Int) {
        require(backstackValue.size >= quantity) { "backstack size must be >= quantity!" }

        repeat(quantity) {
            backstackValue.pop()
        }
        backstackValue.addElement(screen)
        resetValue()
    }

    override fun replaceAll(screen: Screen) {
        if (backstackValue.empty().not()) {
            backstackValue.clear()
        }
        backstackValue.addElement(screen)
        resetValue()
    }

    override fun removeScreens(quantity: Int) {
        require(backstackValue.size > quantity) { "backstack size must be > quantity!" }

        repeat(quantity) {
            backstackValue.pop()
        }
        resetValue()
    }

    override fun removeUntilFirst() {
        repeat(backstackValue.size - 1) {
            backstackValue.pop()
        }
        resetValue()
    }

    private fun resetValue() {
        backstackState = backstackValue.toList()
    }
}