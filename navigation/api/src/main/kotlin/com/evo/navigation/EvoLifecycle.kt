package com.evo.navigation

interface AppLifecycleOwner {

    fun onCreate()

    fun onResume()

    fun onPause()

    fun onDestroy()

}

interface EvoLifecycleOwner {

    fun onEnter()

    fun onExit()

    fun onReturn()

}
