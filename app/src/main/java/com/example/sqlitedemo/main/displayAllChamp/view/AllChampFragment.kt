package com.example.sqlitedemo.main.displayAllChamp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common_android.BaseFragment
import com.example.common_android.navigation.NavigateAction
import com.example.domain.entities.ChampsEntity
import com.example.sqlitedemo.databinding.FragmentShowAllBinding
import com.example.sqlitedemo.main.displayAllChamp.adapter.ChampionAdapter
import com.example.sqlitedemo.main.displayAllChamp.viewmodel.SyncDataViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AllChampFragment : BaseFragment() {
    private lateinit var binding: FragmentShowAllBinding
    private val syncDataViewModel: SyncDataViewModel by sharedViewModel()
    lateinit var adapter : ChampionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowAllBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.syncDataViewModel = syncDataViewModel
        syncDataViewModel.syncListChamps()
        adapter = ChampionAdapter(object : ChampionAdapter.ItemClick{
            override fun onClick(champId: String) {
                navigateTo(GoToDetailChamp(champId))
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.champRcv.setHasFixedSize(true)
        binding.champRcv.adapter = adapter
    }

    object Biding {
        @BindingAdapter("data")
        @JvmStatic
        fun loadData(
            recyclerView: RecyclerView,
            champsEntity: List<ChampsEntity>
        ) {
            (recyclerView.adapter as ChampionAdapter).setData(champsEntity)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllChampFragment()
    }

    data class GoToDetailChamp(
        val champId: String,
    ) : NavigateAction.ToAction()
}