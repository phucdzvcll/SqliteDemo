package com.example.sqlitedemo.main.displayAllChamp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.ChampsEntity

class AppDiffUtils(
    private val oldList: List<ChampsEntity>,
    private val newList: List<ChampsEntity>
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}