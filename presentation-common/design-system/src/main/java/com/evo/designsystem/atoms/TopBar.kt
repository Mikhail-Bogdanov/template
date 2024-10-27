package com.evo.designsystem.atoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.evo.designsystem.theme.DesignSystem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystem.TopBar(
    modifier: Modifier = Modifier,
    title: String,
    colors: TopBarColors = primaryTopBarColors(),
    navigationIcon: Icon? = null,
    actions: List<Icon>? = null,
    expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) = CenterAlignedTopAppBar(
    modifier = modifier,
    title = { Text(text = title, style = textStyles.title, color = colors.titleContentColor) },
    navigationIcon = { navigationIcon?.let { Icon(icon = it) } },
    actions = {
        actions?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                actions.forEach { Icon(icon = it) }
            }
        }
    },
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = colors.containerColor),
    expandedHeight = expandedHeight,
    scrollBehavior = scrollBehavior,
)

@Composable
fun primaryTopBarColors(
    containerColor: Color = DesignSystem.colors.primary,
    titleContentColor: Color = DesignSystem.colors.onPrimary,
) = TopBarColors(
    containerColor = containerColor,
    titleContentColor = titleContentColor,
)

@Composable
fun secondaryTopBarColors(
    containerColor: Color = DesignSystem.colors.secondary,
    titleContentColor: Color = DesignSystem.colors.onSecondary,
) = TopBarColors(
    containerColor = containerColor,
    titleContentColor = titleContentColor,
)

data class TopBarColors(
    val containerColor: Color,
    val titleContentColor: Color,
)
