package com.example.sqlitedemo.features.displayAllChamp.di

import com.example.sqlitedemo.features.displayAllChamp.viewmodel.ShowChampsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val displayChampModule = module {
    viewModel {
        ShowChampsViewModel(
            getListChampsUseCase = get(),
            appDispatchers = get(),
            syncDataUseCase = get(),
        )
    }
}