package com.evo.window

import com.evo.logger.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.MutableStateFlow

class EvoWindowHandlerImpl(
    override val evoLogger: EvoLogger,
) : EvoWindowHandler, Loggable {

    override val dialogChannel: MutableStateFlow<EvoDialog?> = MutableStateFlow(null)

    override val snackChannel: Channel<EvoSnack> = Channel(
        capacity = UNLIMITED,
        onBufferOverflow = BufferOverflow.SUSPEND,
        onUndeliveredElement = { logi(it) },
    )

    override suspend fun showSnack(snack: EvoSnack) = snackChannel.send(snack)
    override suspend fun showDialog(dialog: EvoDialog) = dialogChannel.emit(dialog)
}
