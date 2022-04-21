package com.example.sqlitedemo.main.displayAllChamp.di

import com.example.sqlitedemo.main.displayAllChamp.viewmodel.SyncDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val displayChampModule = module {
    viewModel {
        SyncDataViewModel(
            appDispatchers = get(),
            syncListChampsUseCase = get(),
        )
    }
}