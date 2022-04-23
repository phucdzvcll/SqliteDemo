package com.example.sqlitedemo.feature.champDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.example.common_android.BaseFragment
import com.example.domain.entities.ChampDetailEntity
import com.example.sqlitedemo.MainActivity
import com.example.sqlitedemo.databinding.FragmentDetailChampBinding
import com.example.sqlitedemo.feature.champDetail.viewBinder.HeaderChampDetailViewBinder
import com.example.sqlitedemo.feature.champDetail.viewBinder.TraitsViewBinder
import com.example.sqlitedemo.feature.champDetail.viewModel.DetailChampViewModel
import com.example.sqlitedemo.feature.itemDetail.ItemDialogFragment
import com.example.sqlitedemo.main.displayAllChamp.view.AllChampFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailChamp(private val champId: String) : BaseFragment() {
    lateinit var fragmentDetailChampBinding: FragmentDetailChampBinding
    private val detailChampViewModel: DetailChampViewModel by viewModel()
    private val detailAdapter = MultiTypeAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailChampBinding = FragmentDetailChampBinding.inflate(inflater, container, false)
        fragmentDetailChampBinding.lifecycleOwner = viewLifecycleOwner
        fragmentDetailChampBinding.detailChampViewModel = detailChampViewModel
        detailChampViewModel.getDetailChamp(champId)
        (activity as MainActivity).setSupportActionBar(fragmentDetailChampBinding.toolBar)
        fragmentDetailChampBinding.toolBar.title = champId
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        return fragmentDetailChampBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val headerChampDetailViewBinder = HeaderChampDetailViewBinder(object :
            HeaderChampDetailViewBinder.ItemCLick {
            override fun onLick(itemId: Int) {
                ItemDialogFragment.newInstance(itemId)
                    .show((activity as MainActivity).supportFragmentManager, null)
            }
        })
        val traitViewBinder = TraitsViewBinder(object : TraitsViewBinder.OnChampLeagueTap {
            override fun onClick(champId: String) {
                navigateTo(AllChampFragment.GoToDetailChamp(champId))
            }
        }, champId)
        detailAdapter.register(headerChampDetailViewBinder)
        detailAdapter.register(traitViewBinder)
        fragmentDetailChampBinding.detailRcv.adapter = detailAdapter
    }

    object Binding {
        @BindingAdapter("items")
        @JvmStatic
        fun setItem(
            recyclerView: RecyclerView,
            detailEntity: ChampDetailEntity?
        ) {
            val items = mutableListOf<Any>()

            if (detailEntity != null) {
                items.add(detailEntity.detailHeader)
                items.addAll(detailEntity.traits)
            }
            recyclerView.adapter?.notifyDataSetChanged()
            (recyclerView.adapter as MultiTypeAdapter).items = items
        }
    }

    companion object {
        const val TAG = "DetailChamp"

        @JvmStatic
        fun newInstance(champId: String) =
            DetailChamp(champId = champId)
    }

}