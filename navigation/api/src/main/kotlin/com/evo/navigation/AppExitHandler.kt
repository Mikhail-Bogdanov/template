package com.evo.navigation

import androidx.compose.runtime.staticCompositionLocalOf

interface AppExitHandler {

    fun exit()

}

val LocalExitHandler = staticCompositionLocalOf<AppExitHandler> { error("No AppExitHandler provided") }
