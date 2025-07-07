package com.evo.screen

interface AppLifecycle {

    fun onCreate() {}

    fun onResume() {}

    fun onPause() {}

    fun onDestroy() {}

}

interface ScreenLifecycle : AppLifecycle {

    fun onEnter() {}

    fun onExit() {}

    fun onReturn() {}

}
