package com.example.domain.di

import com.example.domain.usecases.SyncChampionsItemsUseCase
import com.example.domain.usecases.SyncListChampsUseCase
import com.example.domain.usecases.SyncChampionsTraitsUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { SyncListChampsUseCase(champsRepository = get()) }
    factory { SyncChampionsTraitsUseCase(syncDataRepository = get()) }
    factory { SyncChampionsItemsUseCase(syncDataRepository = get()) }
}