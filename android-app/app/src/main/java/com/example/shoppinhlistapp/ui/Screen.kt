package com.example.shoppinhlistapp.ui

sealed class Screen(val route: String)
{
    object LogIn: Screen(route = "logIn_screen")
    object SignUp: Screen(route = "signUp_screen")
}
