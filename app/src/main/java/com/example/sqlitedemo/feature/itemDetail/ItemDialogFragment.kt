package com.example.sqlitedemo.feature.itemDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.FragmentItemDialogBinding
import com.example.sqlitedemo.feature.itemDetail.viewModel.ItemDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDialogFragment(private val itemId: Int) : DialogFragment() {
    lateinit var binding: FragmentItemDialogBinding
    private val itemDetailViewModel: ItemDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.NoMarginsDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDialogBinding.inflate(inflater, container, false)
        itemDetailViewModel.getItem(itemId)
        binding.itemDetailViewModel = itemDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        dialog?.window?.setBackgroundDrawableResource(R.drawable.background_border_radius)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(itemId: Int) =
            ItemDialogFragment(itemId)
    }
}