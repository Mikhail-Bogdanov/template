package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.evo.presentation.ui.conditional
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.background

@Composable
fun DesignSystem.ScreenContent(
    verticalSpace: Dp = DesignSystem.Paddings.DSPx2,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalScroll: Boolean = true,
    topContentPadding: Dp = DesignSystem.Paddings.DSPx4,
    bottomContentPadding: Dp = DesignSystem.Paddings.DSPx2,
    horizontalPadding: Dp = DesignSystem.Paddings.DSPx2,
    content: @Composable ColumnScope.() -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Colors.background.level0)
        .conditional(verticalScroll) {
            verticalScroll(rememberScrollState())
        }
        .padding(horizontal = horizontalPadding)
        .padding(top = topContentPadding, bottom = bottomContentPadding),
    verticalArrangement = Arrangement.spacedBy(verticalSpace, verticalAlignment),
    horizontalAlignment = horizontalAlignment,
    content = content,
)
