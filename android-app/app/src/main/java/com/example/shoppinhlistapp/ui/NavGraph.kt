package com.example.shoppinhlistapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppinhlistapp.ui.pages.LoginPage
import com.example.shoppinhlistapp.ui.pages.signUpPage


@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.LogIn.route)
    {
        composable(
           route = Screen.LogIn.route
        ){
            LoginPage(navController)
        }
        composable(
            route = Screen.SignUp.route
        ){
            signUpPage()
        }
    }
}
