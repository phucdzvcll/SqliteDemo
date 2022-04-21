package com.example.sqlitedemo.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common_android.BaseFragment
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.FragmentDetailChampBinding

class DetailChamp(private val champId: String) : BaseFragment() {
    lateinit var fragmentDetailChampBinding: FragmentDetailChampBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailChampBinding = FragmentDetailChampBinding.inflate(inflater, container, false)
        return fragmentDetailChampBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDetailChampBinding.testNavi.text = champId
    }

    companion object {
        const val TAG = "DetailChamp"
        @JvmStatic
        fun newInstance(champId : String) =
            DetailChamp(champId = champId)
    }

}