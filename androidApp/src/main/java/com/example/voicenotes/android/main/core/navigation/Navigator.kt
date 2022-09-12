package com.example.voicenotes.android.main.core.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class Navigator {

    private val _navigationEvent = Channel<NavEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    suspend fun emitDestination(event: NavEvent) {
        _navigationEvent.send(event)
    }

    fun emitDestinationSync(event: NavEvent) {
        _navigationEvent.trySend(event)
    }
}
