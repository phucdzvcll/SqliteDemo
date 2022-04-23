package com.example.sqlitedemo.main.displayAllChamp.di

import com.example.sqlitedemo.main.displayAllChamp.viewModel.ChampViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val displayChampModule = module{
    viewModel {
        ChampViewModel(
            appDispatchers = get(),
            getAllChampUseCase = get()
        )
    }
}