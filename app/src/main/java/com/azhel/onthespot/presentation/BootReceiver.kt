package com.azhel.onthespot.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.azhel.onthespot.domain.BootEventModel
import com.azhel.onthespot.domain.BootEventRepositoryWrite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootReceiver : BroadcastReceiver(), KoinComponent {

    private val broadcastReceiverScope = CoroutineScope(Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val repository: BootEventRepositoryWrite by inject()

            broadcastReceiverScope.launch {
                val bootTimeMillis = System.currentTimeMillis()
                repository.insertBootEvent(BootEventModel(bootTimeMillis))
            }
        }
    }
}
