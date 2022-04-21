package com.example.sqlitedemo.main.displayAllChamp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.ChampsEntity
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.ItemChampBinding


class ChampionAdapter(private val itemClick: ItemClick) :
    RecyclerView.Adapter<ChampionAdapter.ChampViewHolder>() {
    private val champs: MutableList<ChampsEntity> = mutableListOf()

    class ChampViewHolder(private val binding: ItemChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champsEntity: ChampsEntity) {
            binding.champEntity = champsEntity
            when (champsEntity.cost) {
                1 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gray)
                2 -> binding.imgChamp.setBackgroundResource(R.drawable.border_green)
                3 -> binding.imgChamp.setBackgroundResource(R.drawable.border_blue)
                4 -> binding.imgChamp.setBackgroundResource(R.drawable.border_pink)
                5 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gold)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampViewHolder {
        return ChampViewHolder(
            ItemChampBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
        val champsEntity: ChampsEntity = champs[position]
        holder.bind(champsEntity)
        holder.itemView.setOnClickListener { itemClick.onClick(champsEntity.id) }
    }

    override fun getItemCount() = champs.size

    fun setData(listChamps: List<ChampsEntity>) {
        val diffResult = calculateDiff(AppDiffUtils(this.champs, listChamps))
        this.champs.clear()
        this.champs.addAll(listChamps)
        diffResult.dispatchUpdatesTo(this)
    }

    interface ItemClick {
        fun onClick(champId: String)
    }
}