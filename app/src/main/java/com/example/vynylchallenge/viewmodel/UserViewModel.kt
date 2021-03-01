package com.example.vynylchallenge.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynylchallenge.databinding.FragmentRegisterBinding
import com.example.vynylchallenge.databinding.FragmentSignInBinding
import com.example.vynylchallenge.model.CurrentUser
import com.example.vynylchallenge.model.UserModel
import java.util.regex.Pattern

class UserViewModel() : ViewModel() {

    val user = MutableLiveData<UserModel>()

    fun getName(): String{
        return(CurrentUser.user.name)
    }

    fun getUsername(): String{
        return(CurrentUser.user.username)
    }

    fun select(item: UserModel) {
        user.value = item
    }

    fun handleSignIn(binding: FragmentSignInBinding): Boolean{
        var toastText = "Please enter valid login information."
        val password = binding.password.text.toString()
        val username = binding.username.text.toString()
        val usernameLength = binding.username.text.length
        val passwordLength = binding.password.text.length
        val longLength = 64

        // assign data for main screen
        var userTemp = UserModel()
        userTemp.username = username
        userTemp.password = password
        CurrentUser.user = userTemp

        if(usernameLength >= 4 && passwordLength >= 8 && hasNumber(password)
                && hasLetter(password) && hasSpecialCharacter(password)
                && isNotTooLong(password, longLength) && isNotTooLong(username, longLength)){
            resetSignInInput(binding)
            return true
        }
        else{
            Toast.makeText(binding.root.context, toastText, Toast.LENGTH_LONG).show()
        }
        return false
    }

    fun resetSignInInput(binding: FragmentSignInBinding){
        binding.password.setText("")
        binding.username.setText("")
    }

    fun handleRegister(binding: FragmentRegisterBinding): Boolean{
        var toastText = "Please enter valid registration information."
        val name = binding.password.text.toString()
        val password = binding.password.text.toString()
        val username = binding.username.text.toString()
        val nameLength = binding.username.text.length
        val usernameLength = binding.username.text.length
        val passwordLength = binding.password.text.length
        val longLength = 64

        // assign data for main screen
        var userTemp = UserModel()
        userTemp.name = name
        userTemp.username = username
        userTemp.password = password
        CurrentUser.user = userTemp

        if(usernameLength >= 4 && passwordLength >= 8 && hasNumber(password)
                && hasLetter(password) && hasSpecialCharacter(password)
                && isNotTooLong(password, longLength) && isNotTooLong(username, longLength)
                && isNotTooLong(name, longLength) && nameLength > 0){
            resetRegisterInput(binding)
            return true
        }
        else{
            if(nameLength <= 0){
                toastText += "\nName must be at least 1 charter long."
            }
            if(usernameLength < 4 ){
                toastText += "\nUsername must be at least 4 charters long. "
            }
            if(passwordLength < 8 ){
                toastText += "\nPassword must be at least 8 charters long. "
            }
            // it looks strange to show the user so much if they have not entered
            // anything at all in either field
            if(!(usernameLength <= 0 || passwordLength <= 0 || nameLength <= 0)){
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
                if(!isNotTooLong(name, longLength)){
                    toastText += "\nName must be less than $longLength characters. You have ${name.length}"
                }
            }

            Toast.makeText(binding.root.context, toastText, Toast.LENGTH_LONG).show()
        }
        return false
    }

    fun resetRegisterInput(binding: FragmentRegisterBinding){
        binding.name.setText("")
        binding.password.setText("")
        binding.username.setText("")
    }

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
}