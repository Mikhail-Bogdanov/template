package com.evo.appdata.theme

import com.evo.datastore.EvoDatastoreHandler
import com.evo.datastore.EvoDatastoreKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeHandlerImpl(
    private val themeDatastoreHandler: EvoDatastoreHandler<String>,
) : ThemeHandler {

    private val key = EvoDatastoreKey.StringKey(
        name = THEME_KEY_NAME,
        defaultValue = EvoTheme.DEFAULT.name,
    )

    override fun getTheme(): Flow<EvoTheme> {
        return themeDatastoreHandler.get(key).map { stringName ->
            EvoTheme.valueOf(stringName)
        }
    }

    override suspend fun setDarkTheme() {
        themeDatastoreHandler.set(key, EvoTheme.DARK.name)
    }

    override suspend fun setLightTheme() {
        themeDatastoreHandler.set(key, EvoTheme.LIGHT.name)
    }

    companion object {
        private const val THEME_KEY_NAME = "ThemeKey"
    }
}