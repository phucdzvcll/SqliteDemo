package com.example.sqlitedemo.features.displayAllChamp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sqlitedemo.R
import com.example.sqlitedemo.features.displayAllChamp.adapter.ChampionAdapter
import com.example.sqlitedemo.features.displayAllChamp.viewmodel.ShowChampsViewModel
import kotlinx.android.synthetic.main.fragment_show_all.*
import kotlinx.android.synthetic.main.fragment_show_all.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllChampFragment : Fragment() {
    private val showChampsViewModel: ShowChampsViewModel by viewModel()
    private val adapter: ChampionAdapter = ChampionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showChampsViewModel.getListChamps()
        return inflater.inflate(R.layout.fragment_show_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.champRcv.setHasFixedSize(true)
        view.champRcv.adapter = adapter
        showChampsViewModel.champsListChampsLiveData.observe(viewLifecycleOwner){
            adapter.setupData(it)
        }
        showChampsViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                progress_circular.visibility = View.VISIBLE
            } else {
                progress_circular.visibility = View.GONE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllChampFragment()
    }
}