package com.evo.domain

@JvmInline
value class EvoId(val value: Long)

data class DomainSimpleEntity(
    val evoId: EvoId,
)