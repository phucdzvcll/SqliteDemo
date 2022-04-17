package com.example.sqlitedemo.features.syncData.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.FragmentSyncDialogBinding


class SyncDialogFragment : DialogFragment() {
    lateinit var fragmentSyncDialogBinding: FragmentSyncDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSyncDialogBinding = FragmentSyncDialogBinding.inflate(inflater, container, false)
        return fragmentSyncDialogBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SyncDialogFragment()
    }
}