package com.evo.common.mvi

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class MviViewModel<STATE : MviState, SIDE_EFFECT : MviSideEffect, EVENT : MviEvent>(
    initialState: STATE
) : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {

    override val container = container<STATE, SIDE_EFFECT>(
        initialState = initialState
    )

    abstract fun dispatch(event: EVENT)
}

sealed interface MviSideEffect

sealed interface MviEvent

interface MviState
