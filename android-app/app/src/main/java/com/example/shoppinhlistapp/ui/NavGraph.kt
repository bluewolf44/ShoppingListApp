package com.example.shoppinhlistapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shoppinhlistapp.network.Person
import com.example.shoppinhlistapp.ui.pages.AccessPage
import com.example.shoppinhlistapp.ui.pages.LoginPage
import com.example.shoppinhlistapp.ui.pages.TextPage
import com.example.shoppinhlistapp.ui.pages.listPage
import com.example.shoppinhlistapp.ui.pages.newListPage
import com.example.shoppinhlistapp.ui.pages.signUpPage
import com.example.shoppinhlistapp.ui.viewmodel.SharedViewModel


@Composable
fun SetupNavGraph(navController: NavHostController){
    val sharedViewModel = SharedViewModel()

    NavHost(navController = navController, startDestination = Screen.LogIn.route)
    {
        composable(
           route = Screen.LogIn.route
        ){
            LoginPage(navController,sharedViewModel)
        }
        composable(
            route = Screen.SignUp.route
        ){
            signUpPage(navController,sharedViewModel)
        }
        composable(
            route = Screen.TextScreen.route
        ){
            TextPage(navController,sharedViewModel)
        }
        composable(
            route = Screen.ListScreen.route
        ){
            listPage(navController,sharedViewModel)
        }
        composable(
            route = Screen.NewList.route
        ){
            newListPage(navController,sharedViewModel)
        }
        composable(
            route = Screen.Access.route
        ){
            AccessPage(navController,sharedViewModel)
        }
    }
}
