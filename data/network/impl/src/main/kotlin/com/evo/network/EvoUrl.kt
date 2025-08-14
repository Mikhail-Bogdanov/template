package com.evo.network

import io.ktor.http.URLBuilder
import io.ktor.http.appendPathSegments

class EvoUrl(baseUrl: String) {

    private val urlBuilder = URLBuilder(baseUrl)

    fun appendSegment(segment: Segment): EvoUrl {
        urlBuilder.appendPathSegments(segment.value)
        return this
    }

    fun appendSegment(value: String): EvoUrl {
        urlBuilder.appendPathSegments(value)
        return this
    }

    fun addParam(name: String, value: Any): EvoUrl {
        urlBuilder.parameters.append(name, value.toString())
        return this
    }

    fun build() = urlBuilder.appendPathSegments("/").build()

    sealed class Segment(val value: String) {

        sealed class Evo(value: String) : Segment(value)

    }
}
