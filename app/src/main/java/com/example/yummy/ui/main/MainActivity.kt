package com.example.yummy.ui.main

import android.content.Context
import android.content.Intent
import com.example.yummy.R
import com.example.yummy.base.BaseActivity
import com.example.yummy.ui.favorite.FavoriteFragment
import com.example.yummy.ui.home.HomeFragment
import com.example.yummy.ui.news.NewsFragment
import com.example.yummy.ui.note.NoteFragment
import com.example.yummy.ui.search.AreaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun initComponents() {
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigation)
        bottomNavigationView.selectedItemId = R.id.menuHome
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuHome -> openFragment(HomeFragment())
                R.id.menuSearch -> openFragment(AreaFragment())
                R.id.menuFavorite -> openFragment(FavoriteFragment())
                R.id.menuNews -> openFragment(NewsFragment())
                R.id.menuNote -> openFragment(NoteFragment())
            }
            true
        }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
