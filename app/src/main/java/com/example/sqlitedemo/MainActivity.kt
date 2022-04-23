package com.example.sqlitedemo

import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.common_android.*
import com.example.common_android.navigation.NavigateAction
import com.example.sqlitedemo.feature.champDetail.DetailChamp
import com.example.sqlitedemo.main.displayAllChamp.view.AllChampFragment
import com.example.sqlitedemo.view.MainFragment


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splashScreen.setKeepOnScreenCondition { true }
        Thread {
            SystemClock.sleep(1000) // Sleep 4 seconds
            runOnUiThread {
                splashScreen.setKeepOnScreenCondition { false }
                val fragment = MainFragment.newInstance()
                replaceFragment(fragment, R.id.frameHome)
            }
        }.start()

    }

    override fun onNavigateTo(action: NavigateAction.ToAction) {
        super.onNavigateTo(action)
        if (action is AllChampFragment.GoToDetailChamp) {
            val fragment = DetailChamp.newInstance(action.champId)
            inTransactionToBackStack(fragment, R.id.frameHome, DetailChamp.TAG)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        popFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}