package com.example.sqlitedemo.di

import android.app.Application
import com.example.common_android.di.createCommonModule
import com.example.data.di.createRemoteModule
import com.example.domain.di.createDomainModule
import com.example.sqlitedemo.features.syncData.di.displayChampModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                createCommonModule,
                createRemoteModule,
                createDomainModule,
                displayChampModule,
            )
        }
    }
}