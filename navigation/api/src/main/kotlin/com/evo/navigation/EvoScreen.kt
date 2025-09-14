package com.evo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.evo.presentation.ui.designsystem.atoms.TabDSIcon
import org.koin.core.component.KoinComponent
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

sealed interface EvoContentOwner : EvoLifecycleOwner {

    @Composable
    fun Content()

}

interface ArgsOwner<ARGS : Any> {

    val args: ARGS
}

@OptIn(ExperimentalUuidApi::class)
@Immutable
abstract class BaseScreen : ScreenModelOwner, KoinComponent, EvoContentOwner {

    val id = Uuid.random()

    override fun toString() = """
        ${this::class}(
            id = $id,
            state = ${screenModel.state},
            isActive = ${screenModel.isActive},
        )
    """.trimIndent()

    override fun hashCode() = id.hashCode()

    final override fun equals(other: Any?): Boolean {
        if (other !is BaseScreen) return false

        return id == other.id
    }
}

@Immutable
abstract class BaseTab : BaseScreen(), EvoTabLifecycleOwner {

    abstract val tabIcon: TabDSIcon
}
