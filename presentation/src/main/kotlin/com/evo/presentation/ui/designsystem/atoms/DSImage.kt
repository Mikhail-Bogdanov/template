package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import androidx.compose.foundation.Image as MaterialImage

@Composable
fun DesignSystem.Image(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    image: Painter,
) {
    MaterialImage(
        modifier = modifier,
        painter = image,
        contentDescription = null,
        contentScale = contentScale,
    )
}
