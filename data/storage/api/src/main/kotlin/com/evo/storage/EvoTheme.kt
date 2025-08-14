package com.evo.storage

import kotlinx.coroutines.flow.Flow

// TODO move to theme module
interface ThemeHandler {

    val themeFlow: Flow<EvoTheme>

    suspend fun setDarkTheme()

    suspend fun setLightTheme()
}

enum class EvoTheme {
    LIGHT,
    DARK,
    DEFAULT,
    ;

    val isDefault: Boolean
        get() = this == DEFAULT
}
