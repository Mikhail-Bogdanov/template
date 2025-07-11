package com.evo.screen

interface AppLifecycleOwner {

    fun onCreate() {}

    fun onResume() {}

    fun onPause() {}

    fun onDestroy() {}

}

interface ScreenLifecycleOwner : AppLifecycleOwner {

    fun onEnter() {}

    fun onExit() {}

    fun onReturn() {}

}
