package com.example.sqlitedemo.main.displayAllItems.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.ItemEntity
import com.example.sqlitedemo.databinding.ItemShowBinding

class ShowItemAdapter(private val onItemClick: OnItemClick) : RecyclerView.Adapter<ShowItemAdapter.ItemViewHolder>() {

    private val items: MutableList<ItemEntity> = mutableListOf()

    class ItemViewHolder(private val binding: ItemShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemEntity) {
            binding.itemEntity = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item = item)
        holder.itemView.setOnClickListener {
            onItemClick.onClick(item.id)
        }
    }

    override fun getItemCount() = items.size

    fun setData(items: List<ItemEntity>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface OnItemClick{
        fun onClick(itemId: Int)
    }
}