package com.evo.network

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EvoRequest(baseUrl: String) : KoinComponent {

    private val client: HttpClient by inject()

    private val urlBuilder = URLBuilder(baseUrl)
    val requestBuilder = HttpRequestBuilder()

    fun appendSegment(segment: EvoSegment): EvoRequest {
        urlBuilder.appendPathSegments(segment.value)
        return this
    }

    fun appendSegment(value: String): EvoRequest {
        urlBuilder.appendPathSegments(value)
        return this
    }

    fun addParam(name: String, value: Any): EvoRequest {
        urlBuilder.parameters.append(name, value.toString())
        return this
    }

    inline fun <reified BODY> setBody(body: BODY): EvoRequest {
        requestBuilder.setBody(body)
        return this
    }

    suspend fun request(evoMethod: HttpMethod): HttpResponse {
        val finalRequest = requestBuilder.apply {
            val url = urlBuilder.appendPathSegments("/").build()
            url(url)
            contentType(ContentType.Application.Json)
            setBody(body)
            method = evoMethod
        }
        val response = client.request(finalRequest)
        return response
    }
}

enum class EvoSegment(val value: String) {
    Api("api"),
    V1("v1"),

}
