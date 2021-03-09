package com.example.yummy.ui.favorite

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.ui.adapter.FavoriteViewPagerAdapter
import com.example.yummy.ui.favorite.allfavorite.AllMealFragment
import com.example.yummy.ui.favorite.newfavorite.NewMealFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {
    override val layoutResource get() = R.layout.fragment_favorite

    override fun setupViews() {
        setAdapter()
        setTabsWithViewPager()
    }

    override fun initData() {

    }

    override fun initActions() {

    }

    private fun setAdapter() {
        FavoriteViewPagerAdapter(childFragmentManager).apply {
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
