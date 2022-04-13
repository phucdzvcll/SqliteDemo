package com.example.sqlitedemo.features.displayAllChamp.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.ChampsEntity
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.ItemChampBinding


class ChampionAdapter : RecyclerView.Adapter<ChampionAdapter.ChampViewHolder>() {
    private val champs: MutableList<ViewBinderModel> = mutableListOf()
    private val championLiveData: MutableLiveData<ChampsEntity> = MutableLiveData()

    class ChampViewHolder(private val binding: ItemChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champsEntity: ViewBinderModel) {
            binding.viewBinderModel = champsEntity
            when (champsEntity.champ.cost) {
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
        val champsEntity: ViewBinderModel = champs[position]
        holder.bind(champsEntity)
    }

    override fun getItemCount() = champs.size

    fun setData(listChamps: List<ChampsEntity>) {
        val diffResult = calculateDiff(AppDiffUtils(this.champs, listChamps))
        this.champs.clear()
        this.champs.addAll(listChamps.map {
            ViewBinderModel(
                champ = it,
                itemClickLiveData = championLiveData
            )
        }.toList())
        diffResult.dispatchUpdatesTo(this)
    }

    data class ViewBinderModel(
        val champ: ChampsEntity,
        val itemClickLiveData: MutableLiveData<ChampsEntity>
    ) {
        fun onItemClick() {
            itemClickLiveData.value = champ
        }
    }
}