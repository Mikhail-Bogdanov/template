package com.evo.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey


@Suppress("UNCHECKED_CAST")
internal fun <KeyType> EvoDatastoreKey<KeyType>.asPreferencesKey(): Preferences.Key<KeyType> {
    return when (this) {
        is EvoDatastoreKey.IntKey -> intPreferencesKey(name) as Preferences.Key<KeyType>
        is EvoDatastoreKey.StringKey -> stringPreferencesKey(name) as Preferences.Key<KeyType>
        is EvoDatastoreKey.BooleanKey -> booleanPreferencesKey(name) as Preferences.Key<KeyType>
    }
}
