package com.example.sqlitedemo

import android.os.Bundle
import android.view.MenuItem
import com.example.common_android.*
import com.example.common_android.navigation.NavigateAction
import com.example.sqlitedemo.feature.DetailChamp
import com.example.sqlitedemo.main.displayAllChamp.view.AllChampFragment
import com.example.sqlitedemo.view.MainFragment
import com.example.sqlitedemo.viewmodel.DataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = MainFragment.newInstance()
        replaceFragment(fragment, R.id.frameHome)
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