package com.example.sqlitedemo.di

import android.app.Application
import com.example.common_android.di.createCommonModule
import com.example.data.di.createRemoteModule
import com.example.domain.di.createDomainModule
import com.example.sqlitedemo.feature.detailChampModule
import com.example.sqlitedemo.feature.itemDetail.di.itemDetailModule
import com.example.sqlitedemo.main.displayAllChamp.di.displayChampModule
import com.example.sqlitedemo.main.displayAllItems.di.displayItemModule
import com.example.sqlitedemo.viewmodel.DataViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                createCommonModule,
                createRemoteModule,
                createDomainModule,
                detailChampModule,
                displayChampModule,
                displayItemModule,
                itemDetailModule,
                module {
                    viewModel {
                        DataViewModel(
                            appDispatchers = get(),
                            syncListChampsUseCase = get(),
                        )
                    }
                }
            )
        }
    }
}