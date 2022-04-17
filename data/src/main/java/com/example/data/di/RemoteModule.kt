package com.example.data.di

import com.example.common_jvm.extension.baseUrl
import com.example.common_jvm.extension.connectTimeOut
import com.example.common_jvm.extension.readTimeOut
import com.example.common_jvm.extension.writeTimeOut
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.SqliteRoomDatabase
import com.example.data.local.service.ChampItemsDAO
import com.example.data.mapper.ChampsDBOToEntityMapper
import com.example.data.mapper.ChampsRemoteDBOMapper
import com.example.data.mapper.syncDataMappers.ChampItemsMapper
import com.example.data.mapper.syncDataMappers.ChampTraitsMapper
import com.example.data.remote.ChampionApiService
import com.example.data.remote.SyncDataApiService
import com.example.data.repo.ChampsRepositoryImpl
import com.example.data.repo.SyncDataRepositoryImpl
import com.example.domain.repo.ChampsRepository
import com.example.domain.repo.SyncDataRepository
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
            .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
            .readTimeout(readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(ChampionApiService::class.java) }
    factory { get<Retrofit>().create(SyncDataApiService::class.java) }
    factory { ChampsRemoteDBOMapper() }
    factory { ChampsDBOToEntityMapper() }
    factory { ChampTraitsMapper() }
    factory { ChampItemsMapper() }

    single { SqliteRoomDatabase.getInstance(androidContext()) }
    single { get<SqliteRoomDatabase>().champDAO() }
    single { get<SqliteRoomDatabase>().champTraitsDAO() }
    single { get<SqliteRoomDatabase>().ChampItemsDAO() }


    single<ChampsRepository> {
        ChampsRepositoryImpl(
            remoteExceptionInterceptor = get(),
            champsDBOToEntityMapper = get(),
            champsRemoteDBOMapper = get(),
            champDAO = get(),
            championApiService = get()
        )
    }

    single<SyncDataRepository> {
        SyncDataRepositoryImpl(
            remoteExceptionInterceptor = get(),
            syncDataApiService = get(),
            champDAO = get(),
            champTraitsDAO = get(),
            champTraitsMapper = get(),
            champItemsMapper = get(),
            champsRemoteDBOMapper = get(),
            champItemsDAO = get()
        )
    }
}
