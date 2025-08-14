package com.evo.storage

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

// TODO move to theme module
class ThemeHandlerImpl(
    private val themeDatastoreHandler: EvoStorage,
) : ThemeHandler {

    private val key = EvoStorageSpec.StringSpec(
        name = "ThemeKey",
        defaultValue = EvoTheme.DEFAULT.name,
    )

    override val themeFlow = themeDatastoreHandler.observe(key).map { stringName ->
        EvoTheme.valueOf(stringName)
    }.distinctUntilChanged()

    override suspend fun setDarkTheme() {
        themeDatastoreHandler.set(key, EvoTheme.DARK.name)
    }

    override suspend fun setLightTheme() {
        themeDatastoreHandler.set(key, EvoTheme.LIGHT.name)
    }
}
