package com.azhel.onthespot.presentation

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.azhel.onthespot.domain.BootEventModel
import com.azhel.onthespot.domain.BootEventRepositoryWrite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootCheckWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams),
    KoinComponent {

    private val repository: BootEventRepositoryWrite by inject()

    private val broadcastReceiverScope = CoroutineScope(Dispatchers.IO)

    private var lsatBoot: BootEventModel? = null

    override fun doWork(): Result {

        val boots = broadcastReceiverScope.async(Dispatchers.IO) {
            repository.getAllBootEvents().filter { it.bootTime > (lsatBoot?.bootTime ?: 0) }.sortedBy { it.bootTime }
        }

        broadcastReceiverScope.launch {
            val await = boots.await()

            //TODO post a notification
            when {
                await.isEmpty() -> {

                }
                await.size == 1 -> {

                }
                else -> {

                }
            }
        }

        return Result.success()
    }

}
