package com.example.sqlitedemo.features.syncData.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ChampsEntity
import com.example.domain.entities.SyncChampsItemsEntity
import com.example.domain.entities.SyncChampsTrainsEntity
import com.example.domain.usecases.SyncChampionsItemsUseCase
import com.example.domain.usecases.SyncListChampsUseCase
import com.example.domain.usecases.SyncChampionsTraitsUseCase
import com.example.sqlitedemo.common.CountUpTimer
import com.free.domain.usecases.base.UseCaseParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SyncDataViewModel(
    private val syncListChampsUseCase: SyncListChampsUseCase,
    private val appDispatchers: AppDispatchers,
    private val syncChampionsTraitsUseCase: SyncChampionsTraitsUseCase,
    private val syncChampsItemsUseCase: SyncChampionsItemsUseCase
) : BaseViewModel() {
    private var jobListChamps: Job? = null
    private var syncChampsTraitsJob: Job? = null
    private var syncChampsItemsJob: Job? = null
    val champsListChampsLiveData: MutableLiveData<List<ChampsEntity>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val time: MutableLiveData<Int> = MutableLiveData()
    private val timer = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            time.value = second
        }
    }

     private fun syncChampsItems(){
        syncChampsItemsJob?.cancel()
        syncChampsItemsJob = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, SyncChampsItemsEntity> =
                withContext(appDispatchers.io) {
                    syncChampsItemsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                Log.d("errorNe", failure.toString())
            }, { result ->
                time.value = 1111111
            })
        }
    }

     private fun syncChampsTraits() {
        syncChampsTraitsJob?.cancel()
        syncChampsTraitsJob = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, SyncChampsTrainsEntity> =
                withContext(appDispatchers.io) {
                    syncChampionsTraitsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                Log.d("errorNe", failure.toString())
            }, { result ->
                time.value = 2222222
                syncChampsItems()
            })
        }
    }

    fun syncData() {
        isLoading.value = false
        champsListChampsLiveData.value = listOf()
        jobListChamps?.cancel()
        time.value = 0
        timer.start()
        jobListChamps = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, List<ChampsEntity>> =
                withContext(appDispatchers.io) {
                    syncListChampsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                timer.cancel()
                Log.d("errorNe", failure.toString())
            }, { result ->
                timer.cancel()
                isLoading.value = false
                champsListChampsLiveData.value = result
            })
        }
        syncChampsTraits()
        syncChampsItems()
    }
}