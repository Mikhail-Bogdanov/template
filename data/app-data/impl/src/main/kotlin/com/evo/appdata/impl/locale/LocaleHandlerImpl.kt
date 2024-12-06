package com.evo.appdata.impl.locale

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.evo.appdata.api.locale.EvoLocale
import com.evo.appdata.api.locale.LocaleHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class LocaleHandlerImpl : LocaleHandler {

    private val _evoLocaleFlow = MutableStateFlow(getCurrentAppLocale())
    val evoLocaleFlow = _evoLocaleFlow.asStateFlow()

    private fun getCurrentAppLocale(): EvoLocale {
        val localeList = AppCompatDelegate.getApplicationLocales()
        val currentLocaleTag = if (localeList.isEmpty) null else localeList[0]?.toLanguageTag()
        val currentEvoLocale = EvoLocale.getByLanguageTag(currentLocaleTag)
        return currentEvoLocale
    }

    override suspend fun setLocale(locale: EvoLocale) {
        withContext(Dispatchers.Main) {
            val evoLocaleList = LocaleListCompat.forLanguageTags(locale.languageTag)
            AppCompatDelegate.setApplicationLocales(evoLocaleList)
            _evoLocaleFlow.emit(locale)
        }
    }
}