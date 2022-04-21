package com.example.sqlitedemo.main.displayAllChamp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ChampsEntity
import com.example.domain.usecases.SyncListChampsUseCase
import com.example.sqlitedemo.common.CountUpTimer
import com.free.domain.usecases.base.UseCaseParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SyncDataViewModel(
    private val syncListChampsUseCase: SyncListChampsUseCase,
    private val appDispatchers: AppDispatchers,
) : BaseViewModel() {
    private var jobListChamps: Job? = null
    val syncListChampsLiveData: MutableLiveData<List<ChampsEntity>> = MutableLiveData(listOf())
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val time: MutableLiveData<String> = MutableLiveData("0")
    private val timerSyncListChamp = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            time.value = second.toString()
        }
    }

    fun syncListChamps() {
        timerSyncListChamp.start()
        jobListChamps?.cancel()
        jobListChamps = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, List<ChampsEntity>> =
                withContext(appDispatchers.io) {
                    syncListChampsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure ->
                timerSyncListChamp.cancel()
                isLoading.value = false
                Log.d("errorNe", failure.toString())
            }, {
                timerSyncListChamp.cancel()
                syncListChampsLiveData.value = it
                isLoading.value = false
            })
        }
    }
}