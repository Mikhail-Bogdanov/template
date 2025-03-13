package com.evo.designsystem.atoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.evo.designsystem.theme.DesignSystem

@Composable
fun DesignSystem.BottomBar(
    modifier: Modifier = Modifier,
    containerColor: Color = colors.primary,
    contentColor: Color = colors.onPrimary,
    contentPadding: PaddingValues = PaddingValues(padding.DSPx1),
    actions: List<Icon>,
) = BottomAppBar(
    modifier = modifier,
    containerColor = containerColor,
    contentColor = contentColor,
    content = {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions.forEach { Icon(icon = it) }
        }
    },
    contentPadding = contentPadding,
)