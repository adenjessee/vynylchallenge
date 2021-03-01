package com.example.vynylchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vynylchallenge.databinding.FragmentRegisterBinding
import com.example.vynylchallenge.viewmodel.UserViewModel

class RegisterFragment : Fragment() {

    lateinit var viewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
                inflater, R.layout.fragment_register, container, false
        )

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.register.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {
            if(viewModel.handleRegister(binding)){
                view?.findNavController()?.navigate(R.id.action_registerFragment_to_mainScreenFragment)
            }
        }

        binding.signIn.setOnClickListener {
            viewModel.resetRegisterInput(binding)
            view?.findNavController()?.navigate(R.id.action_registerFragment_to_signInFragment)
        }

        return binding.root
    }
}