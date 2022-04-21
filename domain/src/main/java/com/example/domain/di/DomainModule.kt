package com.example.domain.di

import com.example.domain.usecases.SyncListChampsUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { SyncListChampsUseCase(syncDataRepository = get()) }
}