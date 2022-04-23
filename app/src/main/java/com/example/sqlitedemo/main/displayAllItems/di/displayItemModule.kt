package com.example.sqlitedemo.main.displayAllItems.di

import com.example.sqlitedemo.main.displayAllItems.viewModel.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val displayItemModule = module{
    viewModel {
        ItemViewModel(
            appDispatchers = get(),
            getAllItemUseCase = get()
        )
    }
}