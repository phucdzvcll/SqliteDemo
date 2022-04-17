package com.example.sqlitedemo.features.syncData.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.FragmentSyncDialogBinding
import com.example.sqlitedemo.features.syncData.viewmodel.SyncDataViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SyncDialogFragment : DialogFragment() {
    lateinit var fragmentSyncDialogBinding: FragmentSyncDialogBinding
    private val syncDataViewModel: SyncDataViewModel by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.NoMarginsDialog)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSyncDialogBinding = FragmentSyncDialogBinding.inflate(inflater, container, false)
        fragmentSyncDialogBinding.lifecycleOwner = this
        fragmentSyncDialogBinding.syncDataViewModel = syncDataViewModel
        return fragmentSyncDialogBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        syncDataViewModel.syncListChampsLiveData.observe(viewLifecycleOwner){
//            fragmentSyncDialogBinding.syncListChamp.text = it
//        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SyncDialogFragment()
    }
}