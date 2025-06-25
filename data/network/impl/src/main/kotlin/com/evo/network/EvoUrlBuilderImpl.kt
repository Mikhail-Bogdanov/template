package com.evo.network

import io.ktor.http.URLBuilder
import io.ktor.http.appendPathSegments

class EvoUrlBuilderImpl : EvoUrlBuilder {

    private var urlBuilder: URLBuilder? = null

    override fun setBaseUrl(baseUrl: String) {
        urlBuilder = URLBuilder(baseUrl)
    }

    override fun appendSegment(segment: EvoUrlBuilder.Segment): EvoUrlBuilder {
        urlBuilder?.appendPathSegments(segment.value)
        return this
    }

    override fun appendSegment(value: String): EvoUrlBuilder {
        urlBuilder?.appendPathSegments(value)
        return this
    }

    override fun addParam(name: String, value: Any): EvoUrlBuilder {
        urlBuilder?.parameters?.append(name, value.toString())
        return this
    }

    override fun build() = urlBuilder!!.appendPathSegments("/").build()
}
