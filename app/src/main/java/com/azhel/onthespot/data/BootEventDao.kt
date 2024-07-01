package com.azhel.onthespot.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BootEventDao {
    @Query("SELECT * FROM boot_events ORDER BY boot_time DESC")
    suspend fun getAllBootEvents(): List<BootEventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBootEvent(bootEvent: BootEventEntity)

    @Query("DELETE FROM boot_events")
    suspend fun deleteAll()
}
