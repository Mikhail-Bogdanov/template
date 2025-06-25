package com.evo.network

import io.ktor.http.Url

interface EvoUrlBuilder {

    fun setBaseUrl(baseUrl: String)

    fun appendSegment(segment: Segment): EvoUrlBuilder

    fun appendSegment(value: String): EvoUrlBuilder

    fun addParam(name: String, value: Any): EvoUrlBuilder

    fun build(): Url

    sealed class Segment(val value: String) {

        sealed class Evo(value: String) : Segment(value)

        sealed class Geocoding(value: String) : Segment(value) {

        }

    }
}
