package com.example.myapplication.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.fragments.OnBoardViewPagerFragment

class OnBoardViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int)= OnBoardViewPagerFragment().apply {
        arguments = Bundle().apply {
            putInt(OnBoardViewPagerFragment.ARG_ONBOARD_POSITION, position)
        }
    }
}