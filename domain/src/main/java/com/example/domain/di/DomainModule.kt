package com.example.domain.di

import com.example.domain.usecases.GetListChampsUseCase
import com.example.domain.usecases.SyncDataUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { GetListChampsUseCase(champsRepository = get()) }
    factory { SyncDataUseCase(syncDataRepository = get()) }
}