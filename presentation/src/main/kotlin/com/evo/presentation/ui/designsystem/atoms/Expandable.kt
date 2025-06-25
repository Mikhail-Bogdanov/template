package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.evo.presentation.ui.designsystem.theme.DesignSystem

@Stable
class ExpandableState(
    isExpanded: Boolean = false,
) {

    internal var isExpanded by mutableStateOf(isExpanded)

    fun onExpandedChange(isExpandedNew: Boolean) {
        isExpanded = isExpandedNew
    }

    fun invert() {
        isExpanded = isExpanded.not()
    }

    fun expand() {
        isExpanded = true
    }

    fun collapse() {
        isExpanded = false
    }
}

@Composable
fun DesignSystem.rememberExpandableState(isExpanded: Boolean = false) = remember {
    ExpandableState(isExpanded)
}

@Composable
fun DesignSystem.Expandable(
    modifier: Modifier = Modifier,
    expandableState: ExpandableState = rememberExpandableState(),
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = expandableState.isExpanded,
        modifier = modifier,
        content = content,
        enter = expandVertically(),
        exit = shrinkVertically(),
    )
}
