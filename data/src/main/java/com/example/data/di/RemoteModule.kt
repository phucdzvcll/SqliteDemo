package com.example.data.di

import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.mapper.ChampsListMapper
import com.example.data.remote.ChampionApiService
import com.example.data.repo.ChampsRepositoryImpl
import com.example.domain.repo.ChampsRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val createRemoteModule = module {
    single { RemoteExceptionInterceptor() }

    factory {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://fake-api-json-server-p5k.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(ChampionApiService::class.java) }


    factory { ChampsListMapper() }


    single<ChampsRepository> {
        ChampsRepositoryImpl(
            remoteExceptionInterceptor = get(),
            champsListMapper = get(),
            championApiService = get()
        )
    }
}
