package com.example.myapplication.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieDrawable
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOnBoardViewPagerBinding


class OnBoardViewPagerFragment : Fragment() {
    private val binding by lazy {
        FragmentOnBoardViewPagerBinding.inflate(layoutInflater)
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
        initialize()
    }

    @SuppressLint("SetTextI18n")
    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                tvOnboard.text = getString(R.string.get_fast_result)
                lottieOnboard.setAnimation(R.raw.lottie1)
                lottieOnboard.repeatCount = LottieDrawable.INFINITE
                lottieOnboard.repeatMode = LottieDrawable.REVERSE
                lottieOnboard.playAnimation()
            }
            1 -> {
                tvOnboard.text = getString(R.string.find_your_soulmate)
                lottieOnboard.setAnimation(R.raw.lottie2)
                lottieOnboard.repeatCount = LottieDrawable.INFINITE
                lottieOnboard.repeatMode = LottieDrawable.REVERSE
                lottieOnboard.playAnimation()
            }
            2 -> {
                tvOnboard.text = getString(R.string.easy_start)
                lottieOnboard.setAnimation(R.raw.lottie3)
                lottieOnboard.repeatCount = LottieDrawable.INFINITE
                lottieOnboard.repeatMode = LottieDrawable.REVERSE
                lottieOnboard.playAnimation()
            }
        }

    }


    companion object {
        const val ARG_ONBOARD_POSITION = "onboard"
    }
}