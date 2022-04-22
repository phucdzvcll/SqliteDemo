package com.example.data.di

import com.example.common_jvm.extension.baseUrl
import com.example.common_jvm.extension.connectTimeOut
import com.example.common_jvm.extension.readTimeOut
import com.example.common_jvm.extension.writeTimeOut
import com.example.data.exception_interceptor.RemoteExceptionInterceptor
import com.example.data.local.SqliteRoomDatabase
import com.example.data.mapper.ChampsDBOToEntityMapper
import com.example.data.mapper.detail.ChampByTraitMapper
import com.example.data.mapper.detail.DetailChampHeaderMapper
import com.example.data.mapper.detail.SetDBOtoEntityMapper
import com.example.data.mapper.detail.TraitDBOtoEntityMapper
import com.example.data.mapper.syncDataMappers.*
import com.example.data.remote.SyncDataApiService
import com.example.data.repo.DetailChampRepositoryImpl
import com.example.data.repo.SyncDataRepositoryImpl
import com.example.domain.repo.DetailChampRepository
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

    factory { get<Retrofit>().create(SyncDataApiService::class.java) }
    factory { ChampsRemoteDBOMapper() }
    factory { ChampsDBOToEntityMapper() }
    factory { ChampTraitsMapper() }
    factory { ChampItemsMapper() }
    factory { ItemsRemoteDBOMapper() }
    factory { ChampsDBOEntityMapper() }
    factory { ItemChampDetailMapper() }
    factory { SetRemoteToDBOMapper() }
    factory { TraitRemoteToDBOMapper() }
    factory { SetDBOtoEntityMapper() }
    factory { ChampByTraitMapper() }
    factory { TraitDBOtoEntityMapper(setDBOtoEntityMapper = get(), champByTraitMapper = get()) }
    factory { DetailChampHeaderMapper(itemDetailMapper = get()) }

    single { SqliteRoomDatabase.getInstance(androidContext()) }
    single { get<SqliteRoomDatabase>().champDAO() }
    single { get<SqliteRoomDatabase>().champTraitsDAO() }
    single { get<SqliteRoomDatabase>().ChampItemsDAO() }
    single { get<SqliteRoomDatabase>().ItemsDAO() }
    single { get<SqliteRoomDatabase>().TraitsDAO() }
    single { get<SqliteRoomDatabase>().SetDAO() }


    single<SyncDataRepository> {
        SyncDataRepositoryImpl(
            remoteExceptionInterceptor = get(),
            syncDataApiService = get(),
            champDAO = get(),
            champTraitsDAO = get(),
            champTraitsMapper = get(),
            champItemsMapper = get(),
            champsRemoteDBOMapper = get(),
            itemsRemoteDBOMapper = get(),
            champsDBOEntityMapper = get(),
            traitRemoteToDBOMapper = get(),
            setRemoteToDBOMapper = get(),
            itemsDAO = get(),
            setDAO = get(),
            traitsDAO = get(),
            champItemsDAO = get()
        )
    }

    single<DetailChampRepository> {
        DetailChampRepositoryImpl(
            remoteExceptionInterceptor = get(),
            syncDataApiService = get(),
            champDAO = get(),
            champTraitsDAO = get(),
            champTraitsMapper = get(),
            champItemsMapper = get(),
            champsRemoteDBOMapper = get(),
            itemsRemoteDBOMapper = get(),
            champsDBOEntityMapper = get(),
            traitRemoteToDBOMapper = get(),
            setRemoteToDBOMapper = get(),
            detailChampHeaderMapper = get(),
            traitDBOtoEntityMapper = get(),
            itemsDAO = get(),
            setDAO = get(),
            traitsDAO = get(),
            champItemsDAO = get()
        )
    }
}
