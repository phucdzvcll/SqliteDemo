package com.example.sqlitedemo.features.syncData.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common_android.BaseFragment
import com.example.sqlitedemo.databinding.FragmentSyncDataBinding
import com.example.sqlitedemo.features.syncData.viewmodel.SyncDataViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SyncData() : BaseFragment() {
    private val syncDataViewModel: SyncDataViewModel by sharedViewModel()
    lateinit var fragmentSyncDataBinding: FragmentSyncDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSyncDataBinding = FragmentSyncDataBinding.inflate(inflater, container, false)
        return fragmentSyncDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        syncDataViewModel.syncListChamps()
        syncDataViewModel.syncListItems()
        val newInstance = SyncDialogFragment.newInstance()
        newInstance.isCancelable = false
        newInstance.show(childFragmentManager, "Sync Data...")
    }

    companion object {

        @JvmStatic
        fun newInstance() = SyncData()
    }
}