package com.lcavalle.ft_hangouts

import android.content.BroadcastReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object ReceiverUtils {
    /**
     * some magic i found in stackoverflow. Just to be able to call suspend functions,
     * since we don't have a scope to use here.
     */
    fun BroadcastReceiver.goAsync(
        crContext: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        val pendingResult = goAsync()
        CoroutineScope(SupervisorJob()).launch(crContext) {
            try {
                block()
            } finally {
                pendingResult.finish()
            }
        }
    }
}