package com.example.common_android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.common_android.navigation.NavigateAction
import com.example.common_android.navigation.NavigateViewModel

abstract class BaseActivity : AppCompatActivity() {

    private val navigateViewModel: NavigateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateViewModel.navigateLiveData.observe(this) { action ->
            when (action) {
                is NavigateAction.BackAction -> {
                    navigateBack()
                }
                is NavigateAction.ToAction -> {
                    onNavigateTo(action)
                }

            }
        }
    }

    open fun onNavigateTo(action: NavigateAction.ToAction) {

    }

    open fun navigateBack() {
        onBackPressed()
    }

}