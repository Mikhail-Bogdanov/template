package com.evo.presentation.ui.designsystem.atoms.bar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.ClickInfo
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes.ExtraLarge.asTopBar
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystem.SearchTopBar(
    modifier: Modifier = Modifier,
    searchState: InputFieldState<String>,
    containerColor: Color,
    shape: Shape = DesignSystem.Shapes.Big.asTopBar(),
    navigationIcon: DSIcon? = null,
    actions: ImmutableList<DSIcon>? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    CenterAlignedTopAppBar(
        modifier = modifier.clip(shape),
        title = {
            InputField(
                state = searchState,
                trailingIcon = DSIcon(
                    vector = TODO("Add search top bar"),
                    colors = DesignSystem.onlyIconColors(),
                    clickInfo = if (isFocused) {
                        ClickInfo {
                            searchState.clear()
                            focusManager.clearFocus()
                        }
                    } else {
                        null
                    },
                ),
                interactionSource = interactionSource,
            )
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
}
