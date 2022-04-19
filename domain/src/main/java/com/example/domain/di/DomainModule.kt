package com.example.domain.di

import com.example.domain.usecases.SyncChampionsItemsUseCase
import com.example.domain.usecases.SyncChampionsTraitsUseCase
import com.example.domain.usecases.SyncListChampsUseCase
import com.example.domain.usecases.SyncListItemsUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { SyncListChampsUseCase(syncDataRepository = get()) }
    factory { SyncChampionsTraitsUseCase(syncDataRepository = get()) }
    factory { SyncChampionsItemsUseCase(syncDataRepository = get()) }
    factory { SyncListItemsUseCase(syncDataRepository = get()) }
}