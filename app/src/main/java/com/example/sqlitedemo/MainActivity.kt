package com.example.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.common_android.replaceFragment
import com.example.sqlitedemo.view.MainFragment

class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = MainFragment.newInstance()
        replaceFragment(fragment, R.id.frameHome)
    }
}