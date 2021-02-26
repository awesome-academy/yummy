package com.example.yummy.ui.favorite

import android.os.Bundle
import android.view.View
import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.ui.adapter.FavoriteViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {
    override val layoutResource: Int
        get() = R.layout.fragment_favorite

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setTabsWithViewPager()
    }

    override fun initData() {

    }

    override fun initActions() {

    }

    private fun setAdapter() {
        FavoriteViewPagerAdapter(parentFragmentManager).apply {
            addFragment(AllMealFragment(), getString(R.string.title_all))
            addFragment(NewMealFragment(), getString(R.string.title_new))
            viewPagerFavorite.adapter = this
        }
    }

    private fun setTabsWithViewPager() {
        tabFavorite.apply {
            addTab(tabFavorite.newTab())
            addTab(tabFavorite.newTab())
            tabGravity = TabLayout.GRAVITY_FILL
            setupWithViewPager(viewPagerFavorite)
        }
    }
}
