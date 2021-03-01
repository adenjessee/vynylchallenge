package com.example.vynylchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.vynylchallenge.R
import com.example.vynylchallenge.databinding.FragmentSignInBinding
import com.example.vynylchallenge.viewmodel.UserViewModel

class SignInFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentSignInBinding>(
                inflater, R.layout.fragment_sign_in, container, false
        )

        binding.signIn.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {
            if(viewModel.handleSignIn(binding)){
                view?.findNavController()?.navigate(R.id.action_signInFragment_to_mainScreenFragment)
            }
        }

        binding.register.setOnClickListener {
            viewModel.resetSignInInput(binding)
            view?.findNavController()?.navigate(R.id.action_signInFragment_to_registerFragment)
        }

        return binding.root
    }

}