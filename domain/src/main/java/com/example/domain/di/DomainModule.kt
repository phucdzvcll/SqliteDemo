package com.example.domain.di

import com.example.domain.usecases.GetAllChampUseCase
import com.example.domain.usecases.GetAllItemUseCase
import com.example.domain.usecases.GetDetailChampUseCase
import com.example.domain.usecases.SyncListChampsUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { SyncListChampsUseCase(syncDataRepository = get()) }
    factory { GetDetailChampUseCase(detailChampRepository = get()) }
    factory { GetAllItemUseCase(itemRepository = get()) }
    factory { GetAllChampUseCase(champRepository = get()) }
}