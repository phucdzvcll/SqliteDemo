package com.example.sqlitedemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ChampsEntity
import com.example.domain.entities.ItemEntity
import com.example.domain.entities.SyncDataEntity
import com.example.domain.usecases.GetAllItemUseCase
import com.example.domain.usecases.SyncListChampsUseCase
import com.example.sqlitedemo.common.CountUpTimer
import com.free.domain.usecases.base.UseCaseParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel(
    private val syncListChampsUseCase: SyncListChampsUseCase,
    private val appDispatchers: AppDispatchers,
) : BaseViewModel() {
    private var jobListChamps: Job? = null
    val synChampLiveData: MutableLiveData<SyncDataEntity> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoadContent: MutableLiveData<Boolean> = MutableLiveData(false)
    val isError: MutableLiveData<Boolean> = MutableLiveData(false)
    val process: MutableLiveData<String> = MutableLiveData("")
    fun syncData() {
        isError.value = false
        isLoadContent.value = false
        process.value = "Syncing data, pls wait...."
        isLoading.value = true
        jobListChamps?.cancel()
        jobListChamps = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, SyncDataEntity> =
                withContext(appDispatchers.io) {
                    syncListChampsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure: Failure ->
                isLoading.value = false
                isError.value = true
                when(failure ){
                    is Failure.NetworkError -> process.value = "Connect error!"
                    else -> process.value = "Something went wrong, pls try again!"
                }
                Log.d("errorNe", failure.toString())
            }, {
                isLoading.value = false
                isLoadContent.value = true
                isError.value = false
                synChampLiveData.value = it
                process.value = "Success!"
            })
        }
    }
}