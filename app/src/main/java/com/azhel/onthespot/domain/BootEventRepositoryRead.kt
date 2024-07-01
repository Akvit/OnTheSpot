package com.azhel.onthespot.domain

interface BootEventRepositoryRead {

    suspend fun getAllBootEvents(): List<BootEventModel>


}