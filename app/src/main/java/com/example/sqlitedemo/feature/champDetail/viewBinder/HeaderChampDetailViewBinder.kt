package com.example.sqlitedemo.feature.champDetail.viewBinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.example.domain.entities.ChampDetailEntity
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.ItemHeaderDetailChampBinding


class HeaderChampDetailViewBinder(private val itemClick: ItemCLick) :
    ItemViewBinder<ChampDetailEntity.DetailHeader, HeaderChampDetailViewBinder.HeaderChampDetailHolder>() {

    class HeaderChampDetailHolder(val binding: ItemHeaderDetailChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(headerModel: ChampDetailEntity.DetailHeader) {
            binding.header = headerModel
            when (headerModel.cost) {
                1 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gray)
                2 -> binding.imgChamp.setBackgroundResource(R.drawable.border_green)
                3 -> binding.imgChamp.setBackgroundResource(R.drawable.border_blue)
                4 -> binding.imgChamp.setBackgroundResource(R.drawable.border_pink)
                5 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gold)
            }
        }
    }

    override fun onBindViewHolder(holder: HeaderChampDetailHolder, item: ChampDetailEntity.DetailHeader) {
        holder.bind(headerModel = item)

        holder.binding.item1.setOnClickListener {
            itemClick.onLick(item.items[0].itemId)
        }
        holder.binding.item2.setOnClickListener {
            itemClick.onLick(item.items[1].itemId)
        }
        holder.binding.item3.setOnClickListener {
            itemClick.onLick(item.items[2].itemId)
        }
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HeaderChampDetailHolder {
        val itemBinding = ItemHeaderDetailChampBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return HeaderChampDetailHolder(itemBinding)
    }

    interface ItemCLick {
        fun onLick(itemId: Int)
    }

}