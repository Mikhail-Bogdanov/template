package com.evo.storage

import kotlinx.coroutines.flow.Flow

interface EvoDatastoreHandler<V> {

    fun get(key: EvoDatastoreKey<V>): Flow<V>

    suspend fun set(key: EvoDatastoreKey<V>, value: V)

    suspend fun set(key: EvoDatastoreKey<V>, lazyValue: () -> V)
}