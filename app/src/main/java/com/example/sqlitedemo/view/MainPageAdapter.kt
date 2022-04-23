package com.example.sqlitedemo.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sqlitedemo.main.AboutThisAppFragment
import com.example.sqlitedemo.main.displayAllChamp.view.AllChampFragment
import com.example.sqlitedemo.main.displayAllItems.AllItemFragment

class MainPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val itemPages = mutableListOf<ItemPage>()

    override fun getCount() = itemPages.size

    fun getItemPage(position: Int): ItemPage? {
        return itemPages.getOrNull(position)
    }

    override fun getItem(position: Int): Fragment {
        val page = itemPages[position]
        return when (page.itemType) {
            ItemType.Champs -> AllChampFragment.newInstance()
            ItemType.Items -> AllItemFragment.newInstance()
            ItemType.About -> AboutThisAppFragment.newInstance()
        }
    }

    fun addPage(itemPage: ItemPage) {
        itemPages.add(itemPage)
    }

    data class ItemPage(val title: String, val iconResId: Int, val itemType: ItemType)

    enum class ItemType {
        Champs,
        Items,
        About,
    }
}