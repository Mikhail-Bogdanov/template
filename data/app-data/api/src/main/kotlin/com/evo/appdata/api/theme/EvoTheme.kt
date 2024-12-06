package com.evo.appdata.api.theme

import kotlinx.coroutines.flow.Flow

interface ThemeHandler {

    suspend fun getTheme(): Flow<EvoTheme>

    suspend fun setDarkTheme()

    suspend fun setLightTheme()
}

enum class EvoTheme {
    LIGHT,
    DARK,

    ;

    companion object {
        val DEFAULT = LIGHT
    }
}