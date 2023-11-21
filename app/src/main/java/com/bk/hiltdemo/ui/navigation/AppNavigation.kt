package com.bk.hiltdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bk.hiltdemo.ui.screens.HomeScreen
import com.bk.hiltdemo.ui.screens.MainScreen

@Composable
fun AppNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Routes.HOME){

        composable(Routes.HOME){
            HomeScreen(navHostController)
        }

        composable(Routes.MAIN){
            MainScreen()
        }
    }
}