package com.evo.screen

import android.widget.Toast
import com.evo.domain.extensions.Lambda

interface EvoEventHandler {

    fun event(event: EvoEvent)

}

sealed interface EvoEventType { data object Dialog : EvoEventType
    data object SnackBar : EvoEventType
    data class EvoToast(val toast: Toast) : EvoEventType
}

sealed interface EvoEvent {

    sealed class Confirmation(
        val type: EvoEventType,
        val onConfirm: Lambda,
        val onDeny: Lambda,
    ) : EvoEvent {

        class Delete(
            type: EvoEventType,
            onConfirm: Lambda = {},
            onDeny: Lambda = {},
        ) : Confirmation(type, onConfirm, onDeny)
    }

    data class Undo(
        val onUndo: Lambda,
    ) : EvoEvent

    sealed class Info(val type: EvoEventType) : EvoEvent {

        class Copied(type: EvoEventType) : Info(type)

    }

    data object Exit : EvoEvent

}
