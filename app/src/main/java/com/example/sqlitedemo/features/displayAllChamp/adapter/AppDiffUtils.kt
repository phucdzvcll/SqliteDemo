package com.example.sqlitedemo.features.displayAllChamp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.ChampsEntity

class AppDiffUtils(
    private val oldList: List<ChampionAdapter.ViewBinderModel>,
    private val newList: List<ChampsEntity>
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].champ.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].champ == newList[newItemPosition]
    }
}