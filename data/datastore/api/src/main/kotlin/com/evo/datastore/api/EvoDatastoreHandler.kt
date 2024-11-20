package com.evo.datastore.api

import kotlinx.coroutines.flow.Flow

interface EvoDatastoreHandler<V> {

    suspend fun get(key: EvoDatastoreKey<V>): Flow<V>

    suspend fun set(key: EvoDatastoreKey<V>, value: V)

    suspend fun set(key: EvoDatastoreKey<V>, lazyValue: () -> V)
}