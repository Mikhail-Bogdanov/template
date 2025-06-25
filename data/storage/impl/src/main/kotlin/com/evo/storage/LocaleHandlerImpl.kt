package com.evo.storage

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class LocaleHandlerImpl(
    private val localeDatastoreHandler: StorageHandler<String>,
    private val configuration: Configuration,
) : LocaleHandler {

    private val key = StorageKey.StringKey(
        name = "LocaleKey",
        defaultValue = getCurrentAppLocale(),
    )

    override val localeFlow = localeDatastoreHandler.get(key).map { stringName ->
        EvoLocale.retrieveByTag(stringName)
    }.distinctUntilChanged()

    private fun getCurrentAppLocale(): String {
        val localeList = configuration.locales
        val currentLocaleTag = if (localeList.isEmpty) null else localeList[0]?.toLanguageTag()
        val currentEvoLocale = EvoLocale.retrieveByTag(currentLocaleTag)
        return currentEvoLocale.languageTag
    }

    override suspend fun setLocale(locale: EvoLocale) {
        localeDatastoreHandler.set(key, locale.languageTag)
    }
}
