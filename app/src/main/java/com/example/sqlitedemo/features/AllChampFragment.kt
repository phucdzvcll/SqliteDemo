package com.example.sqlitedemo.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sqlitedemo.R

class AllChampFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_all, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllChampFragment()
    }
}