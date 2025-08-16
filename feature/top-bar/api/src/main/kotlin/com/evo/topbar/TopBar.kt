package com.evo.topbar

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.evo.presentation.ui.SharedComposableArgs
import com.evo.presentation.ui.designsystem.atoms.DSIcon

abstract class TopBar : SharedComposableArgs<TopBarArgs>()

class TopBarArgs {

    var title: String? by mutableStateOf(null)
    val actions: SnapshotStateList<DSIcon> = mutableStateListOf()
    var containerColor: Color? by mutableStateOf(null)
    var titleColor: Color? by mutableStateOf(null)
    var navigationIcon: DSIcon? by mutableStateOf(null)
    var defaultBackButton: Boolean by mutableStateOf(true)
}

interface TopBarOwner {

    val topBarArgs: TopBarArgs

}
