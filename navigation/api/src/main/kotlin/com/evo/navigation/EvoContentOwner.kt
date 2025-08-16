package com.evo.navigation

import androidx.compose.runtime.Composable

sealed interface EvoContentOwner : EvoLifecycleOwner {

    @Composable
    fun Content()

}

interface EvoScreen : EvoContentOwner

interface EvoTab : EvoContentOwner
