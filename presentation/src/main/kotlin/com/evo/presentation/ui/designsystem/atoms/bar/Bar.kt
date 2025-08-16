package com.evo.presentation.ui.designsystem.atoms.bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes.ExtraLarge.asTopBar
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystem.TopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    containerColor: Color,
    titleColor: Color? = null,
    shape: Shape = DesignSystem.Shapes.Big.asTopBar(),
    navigationIcon: DSIcon? = null,
    actions: ImmutableList<DSIcon>? = null,
) = CenterAlignedTopAppBar(
    modifier = modifier.clip(shape),
    title = {
        if (title != null && titleColor != null) {
            Text(text = title, style = DesignSystem.TextStyles.title, color = titleColor)
        }
    },
    navigationIcon = { navigationIcon?.let { Icon(icon = it) } },
    actions = {
        actions?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    DesignSystem.Paddings.DSPx1, Alignment.End
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                actions.forEach { Icon(icon = it) }
            }
        }
    },
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = containerColor),
)
