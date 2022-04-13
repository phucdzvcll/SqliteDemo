package com.example.sqlitedemo.features.displayAllChamp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ChampsEntity
import com.example.domain.usecases.GetListChampsUseCase
import com.example.sqlitedemo.common.CountUpTimer
import com.free.domain.usecases.base.UseCaseParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowChampsViewModel(
    private val getListChampsUseCase: GetListChampsUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {
    var jobListChamps: Job? = null
    val champsListChampsLiveData: MutableLiveData<List<ChampsEntity>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val time: MutableLiveData<Int> = MutableLiveData()
    private val timer = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            time.value = second
        }
    }


    fun getListChamps() {
        isLoading.value = true
        champsListChampsLiveData.value = listOf()
        jobListChamps?.cancel()
        time.value = 0
        timer.start()
        jobListChamps = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, List<ChampsEntity>> =
                withContext(appDispatchers.io) {
                    getListChampsUseCase.execute(UseCaseParams.Empty)
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
    }
}