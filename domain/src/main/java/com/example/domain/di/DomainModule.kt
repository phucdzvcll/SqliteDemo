package com.example.domain.di

import com.example.domain.usecases.*
import org.koin.dsl.module

val createDomainModule = module {
    factory { SyncListChampsUseCase(syncDataRepository = get()) }
    factory { GetDetailChampUseCase(detailChampRepository = get()) }
    factory { GetAllItemUseCase(itemRepository = get()) }
    factory { GetAllChampUseCase(champRepository = get()) }
    factory { GetItemDetailUseCase(itemDetailRepository = get()) }
}