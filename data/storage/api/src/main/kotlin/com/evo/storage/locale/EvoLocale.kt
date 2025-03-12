package com.evo.storage.locale

interface LocaleHandler {

    suspend fun setLocale(locale: EvoLocale)

}

enum class EvoLocale(val languageTag: String?) {
    Default(null),
    Russian("ru"),
    English("en"),

    ;

    companion object {
        fun getByLanguageTag(languageTag: String?) = when (languageTag) {
            English.languageTag -> English
            Russian.languageTag -> Russian
            else -> Default
        }
    }
}