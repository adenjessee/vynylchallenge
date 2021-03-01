package com.example.vynylchallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.vynylchallenge.R
import com.example.vynylchallenge.databinding.FragmentMainScreenBinding
import com.example.vynylchallenge.viewmodel.UserViewModel

class MainScreenFragment : Fragment() {

    lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentMainScreenBinding>(inflater,
            R.layout.fragment_main_screen,container,false)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.usernameDisplay.text = viewModel.getUsername()

        return binding.root
    }
}