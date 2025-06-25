package com.evo.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.evo.internal.SafeWrapper
import kotlinx.coroutines.flow.*

class StorageHandlerImpl<V>(
    private val evoDataStore: DataStore<Preferences>,
) : StorageHandler<V>, SafeWrapper() {

    override suspend fun getAsync(key: StorageKey<V>): V {
        return wrapResultNullable {
            evoDataStore.data.map { prefs ->
                prefs[key.asPreferencesKey()] ?: key.defaultValue
            }.first()
        } ?: key.defaultValue
    }

    override fun get(key: StorageKey<V>): Flow<V> {
        return evoDataStore.data.map { prefs ->
            prefs[key.asPreferencesKey()] ?: key.defaultValue
        }
    }

    override suspend fun set(key: StorageKey<V>, value: V) {
        evoDataStore.edit { mutablePrefs ->
            mutablePrefs[key.asPreferencesKey()] = value
        }
    }

    override suspend fun set(key: StorageKey<V>, lazyValue: () -> V) {
        evoDataStore.edit { mutablePrefs ->
            mutablePrefs[key.asPreferencesKey()] = lazyValue()
        }
    }
}
