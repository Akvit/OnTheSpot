package com.azhel.onthespot.data

import com.azhel.onthespot.domain.BootEventModel
import com.azhel.onthespot.domain.BootEventRepositoryRead
import com.azhel.onthespot.domain.BootEventRepositoryWrite

class BootEventRepositoryReadImpl(private val bootEventDao: BootEventDao): BootEventRepositoryWrite {

    override suspend fun getAllBootEvents(): List<BootEventModel> =
        bootEventDao.getAllBootEvents().map { BootEventModel(it.bootTime) }.sortedBy { it.bootTime }


    override suspend fun insertBootEvent(bootEvent: BootEventModel) =
        bootEventDao.insertBootEvent(BootEventEntity(bootTime = bootEvent.bootTime))

    override suspend fun deleteAllBootEvents() = bootEventDao.deleteAll()
}
