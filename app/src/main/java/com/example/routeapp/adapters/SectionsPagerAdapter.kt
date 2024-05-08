package com.example.routeapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.routeapp.fragments.EasyTrailFragment
import com.example.routeapp.fragments.HardTrailFragment

class SectionsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EasyTrailFragment()
            1 -> HardTrailFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
