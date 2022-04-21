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
    open fun onBackPressed(): Boolean {
        return false
    }

    fun backPressed(): Boolean {
        val fragmentList = childFragmentManager.fragments

        var handled = false
        for (f in fragmentList) {
            if (f is BaseFragment) {
                handled = f.backPressed()
                if (handled) {
                    break
                }
            }
        }

        if (!handled) {
            return onBackPressed()
        }

        return handled
    }
}