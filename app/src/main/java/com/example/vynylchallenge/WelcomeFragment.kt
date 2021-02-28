package com.example.vynylchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.vynylchallenge.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater,
            R.layout.fragment_welcome,container,false)

        binding.signIn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_welcomeFragment_to_signInFragment)
        }

        binding.register.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment)
        }

        return binding.root
    }
}