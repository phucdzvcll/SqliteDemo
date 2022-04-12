package com.example.sqlitedemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common_android.BaseFragment
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.FragmentMainBinding

import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.item_tab_main.view.*

class MainFragment : BaseFragment() {
    private lateinit var mMainBinding: FragmentMainBinding
    private var mainPageAdapter: MainPageAdapter? = null
    private val tabs = mutableListOf<MainPageAdapter.ItemPage>()
    private var currentTab: MainPageAdapter.ItemType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpViewPager()
        initTabLayout()
    }

    private fun initView() {
        mainPageAdapter = MainPageAdapter(childFragmentManager)
        initTabItems()
    }

    private fun initTabItems() {
        tabs.clear()
        tabs.add(
            MainPageAdapter.ItemPage(
                title = "Champions",
                iconResId = R.drawable.selector_icon_tab_show,
                itemType = MainPageAdapter.ItemType.Champs
            )
        )
        tabs.add(
            MainPageAdapter.ItemPage(
                title = "Items",
                iconResId = R.drawable.selector_icon_tab_weapon,
                itemType = MainPageAdapter.ItemType.Items
            )
        )
    }

    private fun initTabLayout() {
        mMainBinding.tlMain.removeAllTabs()
        for (tab in tabs) {
            val tabView: TabLayout.Tab = mMainBinding.tlMain.newTab()
            val customTabView =
                LayoutInflater.from(activity).inflate(R.layout.item_tab_main, null, false)
            customTabView.txtTabHomeTitle.text = tab.title
            customTabView.imgTabHomeIcon.setImageResource(tab.iconResId)
            tabView.customView = customTabView
            mMainBinding.tlMain.addTab(tabView)
        }
        mMainBinding.tlMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //nothing do here
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //nothing do here
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { tabSelected ->
                    val index = tabSelected.position
                    changePage(index)
                }
            }
        })
    }

    private fun changePage(index: Int) {
        val itemPage = mainPageAdapter?.getItemPage(index)
        if (itemPage != null) {
            currentTab = itemPage.itemType
            switchViewPagerPage(index)
        }
    }

    private fun switchViewPagerPage(index: Int) {
        mMainBinding.viewPager.currentItem = index
    }

    private fun setUpViewPager() {
        for (tab in tabs) {
            mainPageAdapter?.addPage(tab)
        }

        mMainBinding.viewPager.adapter = mainPageAdapter
        if (mainPageAdapter?.count != null) {
            mMainBinding.viewPager.offscreenPageLimit = mainPageAdapter?.count!!
        }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}