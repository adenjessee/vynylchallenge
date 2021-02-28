package com.example.vynylchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.vynylchallenge.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentSplashScreenBinding>(inflater,
            R.layout.fragment_splash_screen,container,false)

        //loading our custom made animations
        val animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.fade_in)

        //starting the animation
        binding.splashIcon.startAnimation(animation)

        binding.button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_splashScreenFragment_to_welcomeFragment)
        }

        return binding.root
    }
}