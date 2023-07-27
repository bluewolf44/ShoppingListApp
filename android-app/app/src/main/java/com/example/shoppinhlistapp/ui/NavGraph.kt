package com.example.shoppinhlistapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppinhlistapp.ui.pages.AccessPage
import com.example.shoppinhlistapp.ui.pages.LoginPage
import com.example.shoppinhlistapp.ui.pages.TextPage
import com.example.shoppinhlistapp.ui.pages.listPage
import com.example.shoppinhlistapp.ui.pages.newListPage
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
            signUpPage(navController)
        }
        composable(
            route = Screen.TextScreen.route
        ){
            TextPage(navController)
        }
        composable(
            route = Screen.ListScreen.route
        ){
            listPage(navController)
        }
        composable(
            route = Screen.NewList.route
        ){
            newListPage(navController)
        }
        composable(
            route = Screen.Access.route
        ){
            AccessPage(navController)
        }
    }
}
