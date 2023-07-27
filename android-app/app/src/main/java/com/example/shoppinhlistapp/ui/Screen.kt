package com.example.shoppinhlistapp.ui

sealed class Screen(val route: String)
{
    object LogIn: Screen(route = "logIn_screen")
    object TextScreen: Screen(route = "text_screen")
    object ListScreen: Screen(route = "list_screen")
    object NewList: Screen(route = "newList_screen")
    object Access: Screen(route = "access_screen")
    object SignUp: Screen(route = "signUp_screen")

}
