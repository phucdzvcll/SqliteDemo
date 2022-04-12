package com.example.common_jvm.exception

interface ApiErrorEventPublisher {
    fun onApiError(errorCode: Int, apiUrl: String, errorMessage: String?)
}