package com.evo.network

import com.evo.logger.*
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EvoRequest private constructor(
    private val request: HttpRequestBuilder,
) : KoinComponent, Loggable {

    override val evoLogger: EvoLogger by inject()

    private val client: HttpClient by inject()

    suspend fun execute(evoMethod: HttpMethod): Result<HttpResponse> {
        logi("url: ${request.url}, body: ${request.body}, method: ${request.method}")

        val result = runCatching {
            client.request(request)
        }.onFailure { exeption ->
            loge("exception: ${exeption.message}")
        }.onSuccess { response ->
            logi("successful! Status: ${response.status}, body: ${response.bodyAsText()}")
        }

        return result
    }

    class Builder private constructor(baseUrl: String) {

        private val urlBuilder = URLBuilder(baseUrl)
        val requestBuilder = HttpRequestBuilder()

        fun appendSegment(segment: EvoSegment?) {
            segment?.value?.let {
                urlBuilder.appendPathSegments(it)
            }
        }

        fun appendSegment(value: String?) {
            value?.let {
                urlBuilder.appendPathSegments(it)
            }
        }

        fun addParam(name: String, value: Any?) {
            value?.let {
                urlBuilder.parameters.append(name, it.toString())
            }
        }

        inline fun <reified BODY> setBody(body: BODY) {
            requestBuilder.setBody(body)
        }

        internal fun build(evoMethod: HttpMethod): EvoRequest {
            val requestBuilder = requestBuilder.apply {
                val url = urlBuilder.appendPathSegments("/").build()
                url(url)
                contentType(ContentType.Application.Json)
                setBody(body)
                method = evoMethod
            }
            return EvoRequest(requestBuilder)
        }

        companion object {

            fun build(baseUrl: String, builder: Builder.() -> Unit) {
                Builder(baseUrl).apply(builder)
            }
        }
    }
}

enum class EvoSegment(internal val value: String) {
    Api("api"),
    V1("v1"),

}
