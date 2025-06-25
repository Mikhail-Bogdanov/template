package com.evo.storage

import kotlinx.coroutines.flow.Flow

interface StorageHandler<V> {

    suspend fun getAsync(key: StorageKey<V>): V

    fun get(key: StorageKey<V>): Flow<V>

    suspend fun set(key: StorageKey<V>, value: V)

    suspend fun set(key: StorageKey<V>, lazyValue: () -> V)
}