package com.example.sqlitedemo.features.displayAllChamp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common_android.BaseFragment
import com.example.sqlitedemo.databinding.FragmentShowAllBinding
import com.example.sqlitedemo.features.displayAllChamp.adapter.ChampionAdapter
import com.example.sqlitedemo.features.displayAllChamp.viewmodel.ShowChampsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllChampFragment : BaseFragment() {
    private val showChampsViewModel: ShowChampsViewModel by viewModel()
    private lateinit var binding: FragmentShowAllBinding
    private val adapter: ChampionAdapter = ChampionAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowAllBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        showChampsViewModel.getListChamps()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refreshIndicator.isRefreshing = true
        binding.champRcv.setHasFixedSize(true)
        binding.champRcv.adapter = adapter

        showChampsViewModel.champsListChampsLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        showChampsViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.refreshIndicator.isRefreshing = it
        }

        showChampsViewModel.time.observe(viewLifecycleOwner) {
            binding.timer.text = "$it ms"
        }

        binding.refreshIndicator.setOnRefreshListener {
            showChampsViewModel.getListChamps()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllChampFragment()
    }
}