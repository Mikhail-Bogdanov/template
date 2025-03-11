package com.evo.network.di

import com.evo.network.EvoService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {
    private val module = module {
        factory {
            val okhttpClient = OkHttpClient.Builder().build()

            val mediaType = "application/json".toMediaType()

            Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(EvoService.BASE_URL)
                .addConverterFactory(Json.asConverterFactory(mediaType))
                .build()
                .create(EvoService::class.java)
        }
    }

    operator fun invoke() = module
}