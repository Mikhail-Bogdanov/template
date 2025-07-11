package com.evo.domain

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

@OptIn(ExperimentalSerializationApi::class)
val EvoJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
    allowTrailingComma = true
    namingStrategy = JsonNamingStrategy.SnakeCase
}
