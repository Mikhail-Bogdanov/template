package com.evo.entrypoint

import com.evo.coroutine.ScopeProvider
import kotlinx.coroutines.*

class ScopeProviderImpl : ScopeProvider {

    private var job = SupervisorJob()

    override fun provideIO() = CoroutineScope(Dispatchers.IO + job)

    override fun cancel() {
        job.cancel()
        job = SupervisorJob()
    }
}
