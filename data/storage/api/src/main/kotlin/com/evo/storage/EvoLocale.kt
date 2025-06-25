package com.evo.storage

import kotlinx.coroutines.flow.Flow

interface LocaleHandler {

    val localeFlow: Flow<EvoLocale>

    suspend fun setLocale(locale: EvoLocale)

}

enum class EvoLocale(val languageTag: String?) {
    DEFAULT(null),
    RUSSIAN("ru"),
    ENGLISH("en"),
    ;

    companion object {
        fun getByLanguageTag(languageTag: String?) = when (languageTag) {
            ENGLISH.languageTag -> ENGLISH
            RUSSIAN.languageTag -> RUSSIAN
            else -> DEFAULT
        }
    }
}
