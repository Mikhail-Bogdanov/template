package com.evo.network

import com.evo.network.impl.BuildConfig

class UrlRetrieverImpl : UrlRetriever {

    override fun getEvo() = BuildConfig.EVO_BASE_URL
}
