package com.evo.network

import io.ktor.http.HttpMethod

interface EvoNetwork {

//    suspend fun request(
//        evoRequestMethod: EvoRequestMethod,
//        urlBuilder: EvoUrlBuilder.() -> Url,
//    ): HttpResponse
}

sealed class EvoRequestMethod(val method: HttpMethod) {

    data object Delete : EvoRequestMethod(HttpMethod.Delete)
    data object Get : EvoRequestMethod(HttpMethod.Get)
    data object Post : EvoRequestMethod(HttpMethod.Post)
    data object Put : EvoRequestMethod(HttpMethod.Put)

}
