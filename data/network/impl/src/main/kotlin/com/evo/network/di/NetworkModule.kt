package com.evo.network.di

import com.evo.domain.EvoJson
import com.evo.network.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object NetworkModule {

    private val module = module {
        factory {
            HttpClient(OkHttp) {
                install(ContentNegotiation) {
                    json(EvoJson)
                }
                followRedirects = true
            }
        }
        factoryOf(::EvoNetworkImpl) bind EvoNetwork::class
        factoryOf(::EvoUrlBuilderImpl) bind EvoUrlBuilder::class
    }

    operator fun invoke() = module
}
