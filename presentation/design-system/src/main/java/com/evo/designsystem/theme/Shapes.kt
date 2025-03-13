package com.evo.designsystem.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object Shapes {

    val zero = RoundedCornerShape(0.dp)
    val small = RoundedCornerShape(4.dp)
    val medium = RoundedCornerShape(8.dp)
    val big = RoundedCornerShape(12.dp)
    val large = RoundedCornerShape(20.dp)

    fun custom(size: Dp) = RoundedCornerShape(size)

    fun custom(
        topStart: Dp = 0.dp,
        topEnd: Dp = 0.dp,
        bottomEnd: Dp = 0.dp,
        bottomStart: Dp = 0.dp
    ) = RoundedCornerShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart)
    )
}