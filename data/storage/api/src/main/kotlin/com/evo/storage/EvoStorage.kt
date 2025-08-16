package com.evo.storage

import kotlinx.coroutines.flow.Flow

interface EvoStorage {

    suspend fun <V> getAsync(key: EvoStorageSpec<V>): V

    fun <V> getSync(key: EvoStorageSpec<V>): V

    fun <V> observe(key: EvoStorageSpec<V>): Flow<V>

    suspend fun <V> set(key: EvoStorageSpec<V>, value: V)
}
