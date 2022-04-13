package com.example.sqlitedemo

import android.Manifest
import android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.common_android.replaceFragment
import com.example.sqlitedemo.view.MainFragment


class MainActivity : AppCompatActivity() {
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyStoragePermissions(this)
        askForPermissions()
        val fragment = MainFragment.newInstance()
        replaceFragment(fragment, R.id.frameHome)
    }

    private fun verifyStoragePermissions(activity: Activity?) {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(
            activity!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE,
            )
        }
    }

    private fun askForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(intent)
                return
            }
        }
    }
}