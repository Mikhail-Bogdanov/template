package com.evo.extensions.mviViewModel

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class MviViewModel<STATE : Any, SIDE_EFFECT : Any, EVENT : Any>(
    initialState: STATE
) : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {

    override val container = container<STATE, SIDE_EFFECT>(
        initialState = initialState
    )

    abstract fun dispatch(event: EVENT)
}
