package com.example.sqlitedemo.feature.itemDetail.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common_android.AppDispatchers
import com.example.common_android.BaseViewModel
import com.example.domain.entities.ItemDetailEntity
import com.example.domain.usecases.GetItemDetailUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    private val getItemDetailUseCase: GetItemDetailUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {
    private var job: Job? = null
    val itemDetailLiveData: MutableLiveData<ItemDetailEntity> = MutableLiveData()

    fun getItem(itemId: Int) {
        job?.cancel()
        job = viewModelScope.launch(appDispatchers.main) {
            delay(50)
            val result =
                getItemDetailUseCase.execute(GetItemDetailUseCase.GetItemDetailParam(itemId))
            result.either({}, {
                itemDetailLiveData.value = it
            })
        }
    }
}