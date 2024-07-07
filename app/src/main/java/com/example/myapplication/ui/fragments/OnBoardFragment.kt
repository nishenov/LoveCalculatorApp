package com.example.myapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.FragmentOnBoardBinding
import com.example.myapplication.ui.activities.MainActivity
import com.example.myapplication.ui.adapters.OnBoardViewPagerAdapter


class OnBoardFragment : Fragment() {
    private val binding by lazy {
        FragmentOnBoardBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
        setupListeners()
    }

    private fun setupListeners() = with(binding.viewPager2) {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.tvSkip.visibility = View.INVISIBLE
                    binding.tvStart.visibility = View.VISIBLE
                } else {
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.tvStart.visibility = View.INVISIBLE

                }
            }
        })
        binding.tvSkip.setOnClickListener(){
            if (currentItem < 2){
                setCurrentItem(currentItem + 2, true)
            }
        }
        binding.tvStart.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }
}