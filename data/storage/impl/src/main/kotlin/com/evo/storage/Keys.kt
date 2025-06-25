package com.evo.storage

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey


@Suppress("UNCHECKED_CAST")
internal fun <KeyType> StorageKey<KeyType>.asPreferencesKey(): Preferences.Key<KeyType> {
    return when (this) {
        is StorageKey.IntKey -> intPreferencesKey(name) as Preferences.Key<KeyType>
        is StorageKey.StringKey -> stringPreferencesKey(name) as Preferences.Key<KeyType>
        is StorageKey.BooleanKey -> booleanPreferencesKey(name) as Preferences.Key<KeyType>
        is StorageKey.FloatKey -> floatPreferencesKey(name) as Preferences.Key<KeyType>
    }
}
