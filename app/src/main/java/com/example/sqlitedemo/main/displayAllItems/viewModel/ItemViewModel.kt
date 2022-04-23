package com.example.sqlitedemo.main.displayAllItems.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.domain.entities.ChampsEntity
import com.example.domain.entities.ItemEntity
import com.example.domain.usecases.GetAllChampUseCase
import com.example.domain.usecases.GetAllItemUseCase
import com.example.sqlitedemo.common.CountUpTimer
import com.free.domain.usecases.base.UseCaseParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemViewModel(
    private val getAllItemUseCase: GetAllItemUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {
    private var job: Job? = null
    val itemLiveData: MutableLiveData<List<ItemEntity>> = MutableLiveData(listOf())
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val time: MutableLiveData<String> = MutableLiveData("0")
    private val timerSyncListChamp = object : CountUpTimer(999999999) {
        override fun onTick(second: Int) {
            time.value = second.toString()
        }
    }

    fun getAllItem() {
        time.value = "0"
        timerSyncListChamp.start()
        job?.cancel()
        job = viewModelScope.launch(appDispatchers.main) {
            val result = getAllItemUseCase.execute(UseCaseParams.Empty)
            result.either({
                timerSyncListChamp.cancel()
                isLoading.value = false
            }, {
                itemLiveData.value = it
                timerSyncListChamp.cancel()
                isLoading.value = false
            })
        }
    }
}