package com.example.common_android

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
fun FragmentActivity.replaceFragment(fragment: BaseFragment, frameId: Int) {
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

