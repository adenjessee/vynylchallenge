package com.example.vynylchallenge

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.vynylchallenge.databinding.FragmentSignInBinding
import java.util.regex.Pattern

class SignInFragment : Fragment() {

    private fun hasNumber(password: String): Boolean {
        return password.matches(".*[0-9]+.*".toRegex())
    }

    private fun hasLetter(password: String): Boolean {
        return password.matches(".*[a-zA-Z]+.*".toRegex());
    }

    private fun hasSpecialCharacter(password: String): Boolean {
        val p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val m = p.matcher(password)
        return(m.find())
    }

    private fun isNotTooLong(string: String, length: Int): Boolean {
        return(string.length < length)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentSignInBinding>(
            inflater,
            R.layout.fragment_sign_in, container, false
        )

        binding.signIn.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        {
            var toastText = "Please enter valid login information"
            val password = binding.password.text.toString()
            val username = binding.username.text.toString()
            val usernameLength = binding.username.text.length
            val passwordLength = binding.password.text.length
            val longLength = 64

            if(usernameLength >= 4 && passwordLength >= 8 && hasNumber(password)
                && hasLetter(password) && hasSpecialCharacter(password)
                && isNotTooLong(password, longLength) && isNotTooLong(username, longLength)){
                binding.password.setText("")
                binding.username.setText("")
                view?.findNavController()?.navigate(R.id.action_signInFragment_to_mainScreenFragment)
            }
            else{
                if(usernameLength < 4 ){
                    toastText += "\nUsername must be at least 4 charters long. "
                }
                if(passwordLength < 8 ){
                    toastText += "\nPassword must be at least 8 charters long. "
                }
                // it looks strange to show the user so much if they have not entered
                // anything at all in either field
                if(usernameLength != 0 && passwordLength != 0){
                    if(!hasNumber(password)){
                        toastText += "\nPassword must contain at least 1 number. "
                    }
                    if(!hasLetter(password)){
                        toastText += "\nPassword must contain at least 1 letter. "
                    }
                    if(!hasSpecialCharacter(password)){
                        toastText += "\nPassword must contain at least 1 special character. "
                    }
                    if(!isNotTooLong(username, longLength)){
                        toastText += "\nUsername must be less than $longLength characters. You have ${username.length}"
                    }
                    if(!isNotTooLong(password, longLength)){
                        toastText += "\nPassword must be less than $longLength characters. You have ${password.length}"
                    }
                }

                Toast.makeText(binding.root.context, toastText, Toast.LENGTH_LONG).show()
            }
        }

        binding.register.setOnClickListener {view: View ->
            binding.password.setText("")
            binding.username.setText("")
            view.findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
        }

        return binding.root

    }
}