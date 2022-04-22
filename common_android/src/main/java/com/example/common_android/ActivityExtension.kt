package com.example.common_android

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

inline fun FragmentManager.inTransactionToBackStack(
    name: String,
    func: FragmentTransaction.() -> FragmentTransaction
) {
    beginTransaction().func().addToBackStack(name).commit()
}


fun FragmentActivity.replaceFragment(fragment: BaseFragment, frameId: Int) {
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.inTransaction { setCustomAnimations(
        R.anim.slide_in,
        R.anim.fade_out,
        R.anim.fade_in,
        R.anim.slide_out,
    ).replace(frameId, fragment) }
}

fun FragmentActivity.inTransactionToBackStack(fragment: BaseFragment, frameId: Int, name: String) {
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.inTransactionToBackStack(name) {
        setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out,
        ).replace(frameId, fragment)
    }
}

fun FragmentActivity.popFragment() {
    if (supportFragmentManager.isStateSaved) {
        return
    }
    supportFragmentManager.popBackStack()
}