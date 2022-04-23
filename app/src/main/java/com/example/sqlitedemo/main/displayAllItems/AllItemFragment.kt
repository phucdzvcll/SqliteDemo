package com.example.sqlitedemo.main.displayAllItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common_android.BaseFragment
import com.example.domain.entities.ItemEntity
import com.example.sqlitedemo.databinding.FragmentAllItemBinding
import com.example.sqlitedemo.main.displayAllItems.adapter.ShowItemAdapter
import com.example.sqlitedemo.main.displayAllItems.viewModel.ItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllItemFragment : BaseFragment() {
    lateinit var binding: FragmentAllItemBinding
    private val dataViewModel: ItemViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllItemBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.itemViewModel = dataViewModel
        dataViewModel.getAllItem()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemRcv.adapter = ShowItemAdapter()
    }

    object Biding {
        @BindingAdapter("data")
        @JvmStatic
        fun loadData(
            recyclerView: RecyclerView,
            champsEntity: List<ItemEntity>
        ) {
            (recyclerView.adapter as ShowItemAdapter).setData(champsEntity)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllItemFragment()
    }
}