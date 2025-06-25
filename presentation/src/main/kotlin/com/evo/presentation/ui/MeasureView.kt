package com.evo.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp

@Composable
fun MeasureView(
    viewToMeasure: @Composable () -> Unit,
    content: @Composable (measuredWidth: Dp, measuredHeight: Dp) -> Unit,
) = SubcomposeLayout { constraints ->
    val contentToMeasure = subcompose(
        slotId = "viewToMeasure",
        content = viewToMeasure
    ).first().measure(constraints)

    val measuredWidth = contentToMeasure.width.toDp()
    val measuredHeight = contentToMeasure.height.toDp()

    val finalContent = subcompose(
        slotId = "content",
        content = { content(measuredWidth, measuredHeight) }
    ).first().measure(constraints)

    layout(finalContent.width, finalContent.height) {
        finalContent.place(0, 0)
    }
}
