package com.evo.navigation

interface InitialScreenHandler {

    suspend fun retrieve(): BaseScreen

    suspend fun set(screen: InitialScreen)

    enum class InitialScreen {

        Login,
        Update,
        Start,
        WhatsNew,
    }
}
