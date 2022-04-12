package com.example.common_android.navigation

import androidx.lifecycle.MutableLiveData
import com.example.common_android.BaseViewModel

class NavigateViewModel : BaseViewModel() {

    val navigateLiveData = MutableLiveData<NavigateAction>()

    fun navigateBack() {
        navigateLiveData.value = NavigateAction.BackAction
    }

    fun navigateTo(toNavigation: NavigateAction.ToAction) {
        navigateLiveData.value = toNavigation
    }

}