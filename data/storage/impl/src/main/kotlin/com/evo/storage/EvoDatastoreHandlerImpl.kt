package com.evo.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EvoDatastoreHandlerImpl<V>(
    private val evoDataStore: DataStore<Preferences>,
) : EvoDatastoreHandler<V> {

    override fun get(key: EvoDatastoreKey<V>): Flow<V> {
        return evoDataStore.data.map { prefs ->
            prefs[key.asPreferencesKey()] ?: key.defaultValue
        }
    }

    override suspend fun set(key: EvoDatastoreKey<V>, value: V) {
        evoDataStore.edit { mutablePrefs ->
            mutablePrefs[key.asPreferencesKey()] = value
        }
    }

    override suspend fun set(key: EvoDatastoreKey<V>, lazyValue: () -> V) {
        evoDataStore.edit { mutablePrefs ->
            mutablePrefs[key.asPreferencesKey()] = lazyValue()
        }
    }
}