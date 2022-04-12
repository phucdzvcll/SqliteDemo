package com.example.common_android.di

import com.example.common_android.AppDispatchers
import com.example.common_android.navigation.NavigateViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createCommonModule = module {
    single { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    viewModel { NavigateViewModel() }
}