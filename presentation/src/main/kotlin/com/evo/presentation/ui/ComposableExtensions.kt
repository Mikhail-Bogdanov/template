package com.evo.presentation.ui

import androidx.compose.ui.unit.*

infix operator fun Dp.times(b: Boolean) = takeIf { b } ?: 0.dp
infix operator fun TextUnit.times(b: Boolean) = takeIf { b } ?: TextUnit(0f, type)
