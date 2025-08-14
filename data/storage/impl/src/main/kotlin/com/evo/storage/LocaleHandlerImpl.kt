package com.evo.storage

import android.content.res.Configuration
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

// TODO move to locale module
class LocaleHandlerImpl(
    private val localeDatastoreHandler: EvoStorage,
    private val configuration: Configuration,
) : LocaleHandler {

    private val key = EvoStorageSpec.StringSpec(
        name = "LocaleKey",
        defaultValue = getCurrentAppLocale(),
    )

    override val localeFlow = localeDatastoreHandler.observe(key).map { stringName ->
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
