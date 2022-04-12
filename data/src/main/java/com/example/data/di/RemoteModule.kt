package com.example.data.di

import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.SqliteRoomDatabase
import com.example.data.mapper.ChampsDBOToEntityMapper
import com.example.data.mapper.ChampsRemoteDBOMapper
import com.example.data.mapper.ChampsRemoteToEntityMapper
import com.example.data.remote.ChampionApiService
import com.example.data.repo.ChampsRepositoryImpl
import com.example.domain.repo.ChampsRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
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
    factory { ChampsRemoteToEntityMapper() }
    factory { ChampsRemoteDBOMapper() }
    factory { ChampsDBOToEntityMapper() }

    single { SqliteRoomDatabase.getInstance(androidContext()) }
    single { get<SqliteRoomDatabase>().champDAO() }


    single<ChampsRepository> {
        ChampsRepositoryImpl(
            remoteExceptionInterceptor = get(),
            champsDBOToEntityMapper = get(),
            champsRemoteDBOMapper = get(),
            champsRemoteToEntityMapper = get(),
            champDAO = get(),
            championApiService = get()
        )
    }
}
