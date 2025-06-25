package com.evo.storage

import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class LocaleHandlerImpl(
    private val localeDatastoreHandler: StorageHandler<String>,
) : LocaleHandler {

    private val key = StorageKey.StringKey(
        name = "LocaleKey",
        defaultValue = getCurrentAppLocale(),
    )

    override val localeFlow = localeDatastoreHandler.get(key).map { stringName ->
        EvoLocale.valueOf(stringName)
    }.distinctUntilChanged()

    private fun getCurrentAppLocale(): String {
        val localeList = AppCompatDelegate.getApplicationLocales()
        val currentLocaleTag = if (localeList.isEmpty) null else localeList[0]?.toLanguageTag()
        val currentEvoLocale = EvoLocale.getByLanguageTag(currentLocaleTag)
        return currentEvoLocale.name
    }

    override suspend fun setLocale(locale: EvoLocale) {
        localeDatastoreHandler.set(key, locale.name)
    }
}
