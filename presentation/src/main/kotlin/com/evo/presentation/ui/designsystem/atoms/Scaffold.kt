package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.evo.presentation.ui.conditional
import com.evo.presentation.ui.designsystem.theme.DesignSystem

@Composable
fun DesignSystem.ScreenScaffold(
    topBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    verticalSpace: Dp = DesignSystem.Paddings.DSPx2,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalScroll: Boolean = true,
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    bottomContentPadding: Dp = DesignSystem.Paddings.DSPx2,
    horizontalPadding: Dp = DesignSystem.Paddings.DSPx2,
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = topBar,
        containerColor = Colors.background.level0,
        floatingActionButton = fab,
        snackbarHost = { SnackbarHost(snackBarState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .conditional(verticalScroll) {
                    verticalScroll(rememberScrollState())
                }
                .padding(paddingValues)
                .padding(horizontal = horizontalPadding)
                .fillMaxSize()
                .padding(top = DesignSystem.Paddings.DSPx4, bottom = bottomContentPadding),
            verticalArrangement = Arrangement.spacedBy(verticalSpace, verticalAlignment),
            horizontalAlignment = horizontalAlignment,
            content = content,
        )
    }
}
