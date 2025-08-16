package com.evo.navigation

interface InitialScreenHandler {

    fun get(): EvoContentOwner

    fun set(screen: InitialScreen)

    enum class InitialScreen {

        Login, Update, Start,

    }
}
