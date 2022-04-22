package com.example.sqlitedemo.feature.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.ChampDetailEntity
import com.example.domain.usecases.GetDetailChampUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailChampViewModel(
    private val getDetailChampUseCase: GetDetailChampUseCase,
    private val appDispatchers: AppDispatchers,
) : BaseViewModel() {

    private var jobDetailChamps: Job? = null
    val detailChampsEntity: MutableLiveData<ChampDetailEntity> = MutableLiveData()

    fun getDetailChamp(champId: String) {
        jobDetailChamps?.cancel()
        jobDetailChamps = viewModelScope.launch(appDispatchers.main) {
            val result: Either<Failure, ChampDetailEntity> =
                getDetailChampUseCase.execute(GetDetailChampUseCase.GetDetailChampParam(champId))
            result.either({ failure ->
                //todo handle error
                Log.d("error", "error")
            }, { success ->
                detailChampsEntity.value = success
            })
        }
    }

}