package com.azhel.onthespot.domain

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

//fun BootEventEntity.toModel(): BootEventModel {
//    val calendar = Calendar.getInstance()
//    calendar.timeInMillis = this.bootTime
//
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH) + 1
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//    val hour = calendar.get(Calendar.HOUR_OF_DAY)
//    val minute = calendar.get(Calendar.MINUTE)
//    val second = calendar.get(Calendar.SECOND)
//
//    val result = BootEventModel(
//        year = year, month = month, day = day, hour = hour, minute = minute, second = second
//    )
//    return result
//}
//
//fun BootEventModel.toEntity(): Long {
//    val calendar = Calendar.getInstance()
//    calendar.set(Calendar.YEAR, year)
//    calendar.set(Calendar.MONTH, month - 1)
//    calendar.set(Calendar.DAY_OF_MONTH, day)
//    calendar.set(Calendar.HOUR_OF_DAY, hour)
//    calendar.set(Calendar.MINUTE, minute)
//    calendar.set(Calendar.SECOND, second)
//    return calendar.timeInMillis
//}

fun Long.toDate(): String {
    val instant = Instant.ofEpochMilli(this)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    return localDateTime.format(formatter)
}
