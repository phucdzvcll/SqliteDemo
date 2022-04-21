package com.example.common_android

import android.app.Activity
import android.content.res.Resources
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

inline fun FragmentManager.inTransactionToBackStack(name: String, func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().addToBackStack(name).commit()
}

fun FragmentActivity.addFragment(fragment: BaseFragment, frameId: Int, name: String){
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.inTransactionToBackStack(name) { add(frameId, fragment) }
}

fun FragmentActivity.replaceFragment(fragment: BaseFragment, frameId: Int) {
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}

fun FragmentActivity.inTransactionToBackStack(fragment: BaseFragment, frameId: Int, name: String) {
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.inTransactionToBackStack(name){replace(frameId, fragment)}
}

fun FragmentActivity.popFragment() {
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.popBackStack()
}