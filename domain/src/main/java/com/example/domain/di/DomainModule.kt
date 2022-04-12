package com.example.domain.di

import com.example.domain.usecases.GetListChampsUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { GetListChampsUseCase(champsRepository = get()) }
}