package com.example.data.exception_interceptor

import com.example.common_jvm.exception.ExceptionInterceptor
import com.example.common_jvm.exception.Failure
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class RemoteExceptionInterceptor : ExceptionInterceptor {
    override fun handleException(exception: Exception): Failure? {
        return when (exception) {
            is SSLHandshakeException -> Failure.NetworkError
            is SocketException -> Failure.NetworkError
            is UnknownHostException -> Failure.NetworkError
            is SocketTimeoutException -> Failure.ConnectionTimeout
            is ConnectException -> Failure.ConnectError
            else -> null
        }
    }
}