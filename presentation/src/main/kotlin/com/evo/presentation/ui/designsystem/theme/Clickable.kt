package com.evo.presentation.ui.designsystem.theme

import androidx.compose.runtime.Stable
import com.evo.domain.extensions.Lambda

@Stable
data class ClickInfo(
    val enabled: Boolean = true,
    val onClick: Lambda,
)
