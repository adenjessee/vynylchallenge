package com.example.vynylchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.vynylchallenge.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,
            R.layout.fragment_register,container,false)

        binding.register.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_mainScreenFragment)
        }

        binding.signIn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
        }

        return binding.root
    }
}