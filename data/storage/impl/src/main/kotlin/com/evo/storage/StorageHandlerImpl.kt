package com.evo.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.evo.logger.SafeWrapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class StorageHandlerImpl(
    private val evoDataStore: DataStore<Preferences>,
) : EvoStorage, SafeWrapper() {

    override suspend fun <V> getAsync(key: EvoStorageSpec<V>) = wrapResult {
        evoDataStore.data.map { prefs ->
            prefs[key.asPreferencesKey()] ?: key.defaultValue
        }.first()
    } ?: key.defaultValue

    // TODO think about it!
    override fun <V> getSync(key: EvoStorageSpec<V>) = wrapResult {
        runBlocking {
            evoDataStore.data.map { prefs ->
                prefs[key.asPreferencesKey()] ?: key.defaultValue
            }.first()
        }
    } ?: key.defaultValue

    override fun <V> observe(key: EvoStorageSpec<V>) = wrapResult {
        evoDataStore.data.map { prefs ->
            prefs[key.asPreferencesKey()] ?: key.defaultValue
        }
    } ?: flowOf()

    override suspend fun <V> set(key: EvoStorageSpec<V>, value: V) = wrapAction {
        evoDataStore.edit { mutablePrefs ->
            mutablePrefs[key.asPreferencesKey()] = value
        }
    }
}

@Suppress("UNCHECKED_CAST")
internal fun <KeyType> EvoStorageSpec<KeyType>.asPreferencesKey(): Preferences.Key<KeyType> {
    return when (this) {
        is EvoStorageSpec.IntSpec -> intPreferencesKey(name) as Preferences.Key<KeyType>
        is EvoStorageSpec.StringSpec -> stringPreferencesKey(name) as Preferences.Key<KeyType>
        is EvoStorageSpec.BooleanSpec -> booleanPreferencesKey(name) as Preferences.Key<KeyType>
        is EvoStorageSpec.FloatSpec -> floatPreferencesKey(name) as Preferences.Key<KeyType>
        is EvoStorageSpec.DoubleSpec -> doublePreferencesKey(name) as Preferences.Key<KeyType>
        is EvoStorageSpec.LongSpec -> longPreferencesKey(name) as Preferences.Key<KeyType>
    }
}
