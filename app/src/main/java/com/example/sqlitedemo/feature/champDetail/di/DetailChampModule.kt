package com.example.sqlitedemo.feature

import com.example.sqlitedemo.feature.champDetail.viewModel.DetailChampViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val detailChampModule = module {
    viewModel {
        DetailChampViewModel(
            appDispatchers = get(),
            getDetailChampUseCase = get()
        )
    }
}