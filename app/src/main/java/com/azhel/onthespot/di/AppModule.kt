package com.azhel.onthespot.di

import androidx.room.Room
import com.azhel.onthespot.data.AppDatabase
import com.azhel.onthespot.data.BootEventRepositoryReadImpl
import com.azhel.onthespot.domain.BootEventRepositoryRead
import com.azhel.onthespot.domain.BootEventRepositoryWrite
import com.azhel.onthespot.presentation.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<BootEventRepositoryRead> { BootEventRepositoryReadImpl(get()) }

    single<BootEventRepositoryWrite> { BootEventRepositoryReadImpl(get()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().bootEventDao() }

    viewModel { MainViewModel(get()) }

}
