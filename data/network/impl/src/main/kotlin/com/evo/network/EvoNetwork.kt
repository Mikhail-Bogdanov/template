package com.evo.network

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*

class EvoNetwork(val client: HttpClient) {

    suspend inline fun <reified BODY> request(
        evoMethod: EvoMethod,
        body: BODY,
        url: EvoUrl,
    ): HttpResponse {
        val request = HttpRequestBuilder().apply {
            url(url.build())
            contentType(ContentType.Application.Json)
            setBody(body)
            method = evoMethod.method
        }
        val response = client.request(request)
        return response
    }

    suspend fun request(
        evoMethod: EvoMethod,
        url: EvoUrl,
    ): HttpResponse {
        val request = HttpRequestBuilder().apply {
            url(url.build())
            method = evoMethod.method
        }
        val response = client.request(request)
        return response
    }

    sealed class EvoMethod(val method: HttpMethod) {

        data object Delete : EvoMethod(HttpMethod.Delete)
        data object Get : EvoMethod(HttpMethod.Get)
        data object Post : EvoMethod(HttpMethod.Post)
        data object Put : EvoMethod(HttpMethod.Put)

    }
}
