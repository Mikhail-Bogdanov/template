package com.evo.storage

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ThemeHandlerImpl(
    private val themeDatastoreHandler: StorageHandler<String>,
) : ThemeHandler {

    private val key = StorageKey.StringKey(
        name = "ThemeKey",
        defaultValue = EvoTheme.DEFAULT.name,
    )

    override val themeFlow = themeDatastoreHandler.get(key).map { stringName ->
        EvoTheme.valueOf(stringName)
    }.distinctUntilChanged()

    override suspend fun setDarkTheme() {
        themeDatastoreHandler.set(key, EvoTheme.DARK.name)
    }

    override suspend fun setLightTheme() {
        themeDatastoreHandler.set(key, EvoTheme.LIGHT.name)
    }
}
