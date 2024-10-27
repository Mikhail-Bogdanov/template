package com.evo.extensions

import androidx.compose.runtime.Stable

@Stable
data class ClickInfo(
    val enabled: Boolean = true,
    val onClick: Lambda,
)