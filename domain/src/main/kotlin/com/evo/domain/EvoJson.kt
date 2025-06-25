package com.evo.domain

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val EvoJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
    allowTrailingComma = true
}
