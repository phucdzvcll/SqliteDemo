package com.example.sqlitedemo.feature.itemDetail.di

import com.example.sqlitedemo.feature.itemDetail.viewModel.ItemDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val itemDetailModule = module {
    viewModel {
        ItemDetailViewModel(
            appDispatchers = get(),
            getItemDetailUseCase = get()
        )
    }
}