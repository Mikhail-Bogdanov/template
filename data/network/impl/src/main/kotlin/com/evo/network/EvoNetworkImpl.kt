package com.evo.network

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*

class EvoNetworkImpl(val client: HttpClient) : EvoNetwork {

    suspend inline fun <reified BODY> request(
        evoRequestMethod: EvoRequestMethod,
        body: BODY,
        urlBuilder: EvoUrlBuilder.() -> Url,
    ): HttpResponse {
        val url = EvoUrlBuilderImpl().urlBuilder()
        val request = HttpRequestBuilder().apply {
            url(url)
            contentType(ContentType.Application.Json)
            setBody(body)
            method = evoRequestMethod.method
        }
        val response = client.request(request)
        return response
    }

    suspend fun request(
        evoRequestMethod: EvoRequestMethod,
        urlBuilder: EvoUrlBuilder.() -> Url,
    ): HttpResponse {
        val url = EvoUrlBuilderImpl().urlBuilder()
        val request = HttpRequestBuilder().apply {
            url(url)
            method = evoRequestMethod.method
        }
        val response = client.request(request)
        return response
    }
}
