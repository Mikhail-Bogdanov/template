package com.evo.di

import org.koin.core.module.Module

abstract class EvoModule {

    abstract fun Module.initialize()

}
