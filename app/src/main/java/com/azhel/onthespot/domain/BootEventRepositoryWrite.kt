package com.azhel.onthespot.domain

interface BootEventRepositoryWrite: BootEventRepositoryRead {

    suspend fun insertBootEvent(bootEvent: BootEventModel)

    suspend fun deleteAllBootEvents()

}