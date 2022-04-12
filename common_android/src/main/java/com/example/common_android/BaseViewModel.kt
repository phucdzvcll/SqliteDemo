package com.example.common_android


import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    open fun onCreated() {

    }

    open fun onDestroyed() {

    }
}