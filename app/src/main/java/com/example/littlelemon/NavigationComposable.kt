package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(sharedPreferences: SharedPreferences, databaseMenuItems: List<MenuItemRoom>){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = if(sharedPreferences.contains("email")){Home.route}else{Onboarding.route})
        {composable(Home.route) {
            Home(navController, databaseMenuItems)
            }
            composable(Onboarding.route){
                Onboarding(sharedPreferences, navController)
            }
            composable(Profile.route){
                Profile(sharedPreferences, navController)
            }
        }
}
