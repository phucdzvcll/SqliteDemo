package com.example.sqlitedemo.features.syncData.di

import com.example.sqlitedemo.features.syncData.viewmodel.SyncDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val displayChampModule = module {
    viewModel {
        SyncDataViewModel(
            appDispatchers = get(),
            syncChampionsTraitsUseCase = get(),
            syncChampsItemsUseCase = get(),
            syncListChampsUseCase = get(),
        )
    }
}