package com.example.sqlitedemo.features.syncData.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.functional.Either
import com.example.domain.entities.SyncChampsItemsEntity
import com.example.domain.entities.SyncChampsTrainsEntity
import com.example.domain.entities.SyncListChampsEntity
import com.example.domain.entities.SyncListItemsEntity
import com.example.domain.usecases.SyncChampionsItemsUseCase
import com.example.domain.usecases.SyncListChampsUseCase
import com.example.domain.usecases.SyncChampionsTraitsUseCase
import com.example.domain.usecases.SyncListItemsUseCase
import com.example.sqlitedemo.common.CountUpTimer
import com.free.domain.usecases.base.UseCaseParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SyncDataViewModel(
    private val syncListChampsUseCase: SyncListChampsUseCase,
    private val appDispatchers: AppDispatchers,
    private val syncChampionsTraitsUseCase: SyncChampionsTraitsUseCase,
    private val syncListItemsUseCase: SyncListItemsUseCase,
    private val syncChampsItemsUseCase: SyncChampionsItemsUseCase
) : BaseViewModel() {
    private var jobListChamps: Job? = null
    private var syncChampsTraitsJob: Job? = null
    private var syncChampsItemsJob: Job? = null
    val syncListChampsLiveData: MutableLiveData<String> = MutableLiveData("")
    val syncListChampsTraitsLiveData: MutableLiveData<String> = MutableLiveData("")
    val syncListChampsItemsLiveData: MutableLiveData<String> = MutableLiveData("")
    val syncListItemsLiveData: MutableLiveData<String> = MutableLiveData("")
    private val timerSyncListChamp = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            if (second % 500 == 0) {
                val oldValue: String = syncListChampsLiveData.value.defaultEmpty()
                if (oldValue.replace(" ", "") == "........") {
                    syncListChampsLiveData.value = ""
                } else {
                    syncListChampsLiveData.value = "$oldValue ."
                }
            }
        }
    }
    private val timerSyncListItems = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            if (second % 500 == 0) {
                val oldValue: String = syncListItemsLiveData.value.defaultEmpty()
                if (oldValue.replace(" ", "") == "........") {
                    syncListItemsLiveData.value = ""
                } else {
                    syncListItemsLiveData.value = "$oldValue ."
                }
            }
        }
    }

    private val timerSyncChampTrait = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            if (second % 500 == 0) {
                val oldValue: String = syncListChampsTraitsLiveData.value.defaultEmpty()
                if (oldValue.replace(" ", "") == "........") {
                    syncListChampsTraitsLiveData.value = ""
                } else {
                    syncListChampsTraitsLiveData.value = "$oldValue ."
                }
            }
        }
    }

    private val timerSyncChampItems = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            if (second % 500 == 0) {
                val oldValue: String = syncListChampsItemsLiveData.value.defaultEmpty()
                if (oldValue.replace(" ", "") == "........") {
                    syncListChampsItemsLiveData.value = ""
                } else {
                    syncListChampsItemsLiveData.value = "$oldValue ."
                }
            }
        }
    }

    private fun syncChampsItems() {
        timerSyncChampItems.start()
        syncChampsItemsJob?.cancel()
        syncChampsItemsJob = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, SyncChampsItemsEntity> =
                withContext(appDispatchers.io) {
                    syncChampsItemsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                timerSyncChampItems.cancel()
                syncListChampsItemsLiveData.value = failure.toString()
                Log.d("errorNe", failure.toString())
            }, { result ->
                timerSyncChampItems.cancel()
            })
        }
    }

    private fun syncChampsTraits() {
        timerSyncChampTrait.start()
        syncChampsTraitsJob?.cancel()
        syncChampsTraitsJob = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, SyncChampsTrainsEntity> =
                withContext(appDispatchers.io) {
                    syncChampionsTraitsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                timerSyncChampTrait.cancel()
                syncListChampsTraitsLiveData.value = failure.toString()
                Log.d("errorNe", failure.toString())
            }, { result ->
                timerSyncChampTrait.cancel()
            })
        }
    }

    fun syncListChamps() {
        syncListChampsLiveData.value = "."
        timerSyncListChamp.start()
        jobListChamps?.cancel()
        jobListChamps = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, SyncListChampsEntity> =
                withContext(appDispatchers.io) {
                    syncListChampsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                syncListChampsLiveData.value = "error"
                timerSyncListChamp.cancel()
                Log.d("errorNe", failure.toString())
            }, {
                timerSyncListChamp.cancel()
                syncListChampsLiveData.value = " ms"
            })
        }
    }
    fun syncListItems() {
        syncListItemsLiveData.value = "."
        timerSyncListItems.start()
        jobListChamps?.cancel()
        jobListChamps = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, SyncListItemsEntity> =
                withContext(appDispatchers.io) {
                    syncListItemsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                syncListItemsLiveData.value = "error"
                timerSyncListItems.cancel()
                Log.d("errorNe", failure.toString())
            }, {
                timerSyncListItems.cancel()
                syncListItemsLiveData.value = " ms"
            })
        }
    }
}