package com.example.yummy.utlis

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.addFragment(layout: Int, fragment: Fragment) {
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .add(layout, fragment)
        .addToBackStack(null)
        .commit()
}

fun FragmentManager.removeFragment(layout: Int, fragment: Fragment) {
    popBackStack()
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        .remove(fragment)
        .commit()
}

fun FragmentManager.replaceFragment(layout: Int, fragment: Fragment) {
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(layout, fragment)
        .addToBackStack(null)
        .commit()
}
