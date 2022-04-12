package com.example.common_android

import androidx.fragment.app.Fragment
import com.example.common_android.navigation.NavigateAction
import com.example.common_android.navigation.NavigateViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment : Fragment() {
    private val navigateViewModel by sharedViewModel<NavigateViewModel>()

    fun navigateTo(action: NavigateAction.ToAction) {
        navigateViewModel.navigateTo(action)
    }

    fun navigateBack() {
        navigateViewModel.navigateBack()
    }
}