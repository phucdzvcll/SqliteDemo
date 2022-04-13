package com.example.sqlitedemo.features.displayAllChamp.adapter

import android.R.attr.data
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.ChampsEntity
import com.example.sqlitedemo.R
import kotlinx.android.synthetic.main.item_champ.view.*


class ChampionAdapter : RecyclerView.Adapter<ChampionAdapter.ChampViewHolder>() {
    private val champs: MutableList<ChampsEntity> = mutableListOf()

    class ChampViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampViewHolder {
        return ChampViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_champ, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
        val champsEntity: ChampsEntity = champs[position]
        holder.itemView.name.text = champsEntity.name
        when (champsEntity.cost) {
            1 -> holder.itemView.imgChamp.setBackgroundResource(R.drawable.border_gray)
            2 -> holder.itemView.imgChamp.setBackgroundResource(R.drawable.border_green)
            3 -> holder.itemView.imgChamp.setBackgroundResource(R.drawable.border_blue)
            4 -> holder.itemView.imgChamp.setBackgroundResource(R.drawable.border_pink)
            5 -> holder.itemView.imgChamp.setBackgroundResource(R.drawable.border_gold)
        }

        if (champsEntity.imgPath.isNotEmpty()) {
            val myBitmap = BitmapFactory.decodeFile(champsEntity.imgPath)
            holder.itemView.imgChamp.setImageBitmap(myBitmap)
        } else {
            Glide.with(holder.itemView.imgChamp.context)
                .load(champsEntity.imgUrl)
                .into(holder.itemView.imgChamp)
        }
    }

    override fun getItemCount() = champs.size

    fun setData(listChamps: List<ChampsEntity>) {
        val diffResult = calculateDiff(AppDiffUtils(this.champs, listChamps))
        this.champs.clear()
        this.champs.addAll(listChamps)
        diffResult.dispatchUpdatesTo(this)
    }
}