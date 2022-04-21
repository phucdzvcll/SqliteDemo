package com.example.sqlitedemo.main.displayAllItems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sqlitedemo.R

class AllItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_item, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = AllItemFragment()
    }
}