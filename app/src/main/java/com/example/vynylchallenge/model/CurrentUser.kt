package com.example.vynylchallenge.model

object CurrentUser{
    lateinit var user: UserModel
    fun set(userIn: UserModel){
        user = userIn
    }
}

