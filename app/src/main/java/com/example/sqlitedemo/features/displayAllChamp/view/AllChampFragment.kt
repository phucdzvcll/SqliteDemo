package com.example.sqlitedemo.features.displayAllChamp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common_android.BaseFragment
import com.example.sqlitedemo.databinding.FragmentShowAllBinding
import com.example.sqlitedemo.features.displayAllChamp.adapter.ChampionAdapter

class AllChampFragment : BaseFragment() {
    private lateinit var binding: FragmentShowAllBinding
    private val adapter: ChampionAdapter = ChampionAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowAllBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.champRcv.setHasFixedSize(true)
        binding.champRcv.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllChampFragment()
    }
}