package com.example.sqlitedemo.common

import android.os.CountDownTimer


abstract class CountUpTimer(private val duration: Long) :
    CountDownTimer(duration, INTERVAL_MS) {
    abstract fun onTick(second: Int)
    override fun onTick(msUntilFinished: Long) {
        val second = ((duration - msUntilFinished) / 1).toInt()
        onTick(second)
    }

    override fun onFinish() {
        onTick(duration / 1)
    }

    companion object {
        private const val INTERVAL_MS: Long = 1
    }
}