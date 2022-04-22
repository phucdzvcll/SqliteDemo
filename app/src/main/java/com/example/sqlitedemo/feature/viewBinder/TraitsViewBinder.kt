package com.example.sqlitedemo.feature.viewBinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.example.domain.entities.ChampDetailEntity
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.ItemChampLeagueBinding
import com.example.sqlitedemo.databinding.TraitDetailItemBinding
import com.example.sqlitedemo.databinding.TraitSetItemBinding

class TraitsViewBinder(
    private val onChampLeagueTap: OnChampLeagueTap,
    private val currentChampId: String
) :
    ItemViewBinder<ChampDetailEntity.Trait, TraitsViewBinder.TraitsViewHolder>() {

    class TraitsViewHolder(val binding: TraitDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            trait: ChampDetailEntity.Trait,
            onChampLeagueTap: OnChampLeagueTap,
            currentChampId: String
        ) {
            binding.trait = trait
            binding.setAdapter = SetAdapter()
            binding.champLeagueAdapter = ChampLeagueAdapter(onChampLeagueTap, currentChampId)
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): TraitsViewHolder {
        val itemBinding = TraitDetailItemBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return TraitsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TraitsViewHolder, item: ChampDetailEntity.Trait) {
        holder.bind(item, onChampLeagueTap, currentChampId)
    }

    class SetAdapter : RecyclerView.Adapter<SetAdapter.SetViewHolder>() {
        private val sets: MutableList<ChampDetailEntity.Trait.Set> = mutableListOf()

        class SetViewHolder(private val setBinding: TraitSetItemBinding) :
            RecyclerView.ViewHolder(setBinding.root) {

            fun bind(set: ChampDetailEntity.Trait.Set) {
                setBinding.set = set
                when (set.style) {
                    "bronze" -> setBinding.setActive.setBackgroundResource(R.drawable.set_brone)
                    "silver" -> setBinding.setActive.setBackgroundResource(R.drawable.set_silver)
                    "gold" -> setBinding.setActive.setBackgroundResource(R.drawable.set_gold)
                    "chromatic" -> setBinding.setActive.setBackgroundResource(R.drawable.set_dynamon)
                    else -> setBinding.setActive.setBackgroundResource(R.drawable.set_brone)
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
            val itemBinding = TraitSetItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
            return SetViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
            holder.bind(sets[position])
        }

        override fun getItemCount() = sets.size

        fun setData(sets: List<ChampDetailEntity.Trait.Set>) {
            this.sets.clear()
            this.sets.addAll(sets)
        }
    }

    class ChampLeagueAdapter(
        private val onChampLeagueTap: OnChampLeagueTap,
        private val currentChampId: String
    ) : RecyclerView.Adapter<ChampLeagueAdapter.ChampLeagueViewHolder>() {
        private val champLeagues: MutableList<ChampDetailEntity.Trait.ChampLeague> =
            mutableListOf()

        class ChampLeagueViewHolder(private val setBinding: ItemChampLeagueBinding) :
            RecyclerView.ViewHolder(setBinding.root) {

            fun bind(champLeagues: ChampDetailEntity.Trait.ChampLeague) {
                setBinding.champEntity = champLeagues
                when (champLeagues.cost) {
                    1 -> setBinding.imgChamp.setBackgroundResource(R.drawable.border_gray)
                    2 -> setBinding.imgChamp.setBackgroundResource(R.drawable.border_green)
                    3 -> setBinding.imgChamp.setBackgroundResource(R.drawable.border_blue)
                    4 -> setBinding.imgChamp.setBackgroundResource(R.drawable.border_pink)
                    5 -> setBinding.imgChamp.setBackgroundResource(R.drawable.border_gold)
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampLeagueViewHolder {
            val itemBinding = ItemChampLeagueBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
            return ChampLeagueViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: ChampLeagueViewHolder, position: Int) {
            val champLeague = champLeagues[position]
            holder.bind(champLeague)
            holder.itemView.setOnClickListener {
                if (currentChampId != champLeague.id) {
                    onChampLeagueTap.onClick(champLeague.id)
                }
            }
        }

        override fun getItemCount() = champLeagues.size

        fun setData(champLeagues: List<ChampDetailEntity.Trait.ChampLeague>) {
            this.champLeagues.clear()
            this.champLeagues.addAll(champLeagues)
        }
    }

    object Binding {
        @BindingAdapter(value = ["setItems", "adapter"], requireAll = true)
        @JvmStatic
        fun setItem(
            recyclerView: RecyclerView,
            sets: List<ChampDetailEntity.Trait.Set>,
            setAdapter: SetAdapter
        ) {
            recyclerView.adapter = setAdapter
            setAdapter.setData(sets)
            setAdapter.notifyDataSetChanged()
        }

        @BindingAdapter(value = ["champLeagues", "champLeagueAdapter"], requireAll = true)
        @JvmStatic
        fun setLeague(
            recyclerView: RecyclerView,
            champLeagues: List<ChampDetailEntity.Trait.ChampLeague>,
            champLeagueAdapter: ChampLeagueAdapter
        ) {
            recyclerView.adapter = champLeagueAdapter
            champLeagueAdapter.setData(champLeagues)
            champLeagueAdapter.notifyDataSetChanged()
        }
    }

    interface OnChampLeagueTap {
        fun onClick(champId: String)
    }
}