package com.evo.storage

import kotlinx.coroutines.flow.Flow

// TODO move to locale module
interface LocaleHandler {

    val localeFlow: Flow<EvoLocale>

    suspend fun setLocale(locale: EvoLocale)

}

sealed class EvoLocale(val languageTag: String) {

    data object Russian : EvoLocale("ru")
    data object English : EvoLocale("en")

    companion object {

        val Default = Russian

        fun retrieveByTag(tag: String?) = when {
            tag == null || tag.contains(Russian.languageTag, true) -> Russian
            else -> English
        }
    }
}
