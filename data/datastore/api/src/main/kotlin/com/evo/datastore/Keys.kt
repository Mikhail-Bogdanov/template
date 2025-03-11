package com.evo.datastore


sealed interface EvoDatastoreKey<D> {
    val name: String
    val defaultValue: D

    data class IntKey(
        override val name: String,
        override val defaultValue: Int,
    ) : EvoDatastoreKey<Int>

    data class StringKey(
        override val name: String,
        override val defaultValue: String,
    ) : EvoDatastoreKey<String>

    data class BooleanKey(
        override val name: String,
        override val defaultValue: Boolean,
    ) : EvoDatastoreKey<Boolean>
}