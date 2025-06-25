package com.evo.presentation.ui.designsystem.atoms.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.*
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystem.TopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    containerColor: Color,
    titleColor: Color? = null,
    shape: DesignSystem.Shapes.Bar.Top = DesignSystem.Shapes.Bar.Top.Big,
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

@Composable
fun DesignSystem.BottomBar(
    modifier: Modifier = Modifier,
    containerColor: Color,
    shape: DesignSystem.Shapes.Bar.Bottom,
    contentPadding: PaddingValues = PaddingValues(DesignSystem.Paddings.DSPx1),
    actions: ImmutableList<DSIcon>,
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .clip(shape)
        .background(containerColor)
        .padding(contentPadding),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically,
) {
    actions.forEach {
        Icon(icon = it)
    }
}
