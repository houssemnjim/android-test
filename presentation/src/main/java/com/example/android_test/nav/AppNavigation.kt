package com.example.android_test.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_test.art_details.ArtDetailsViewModel
import com.example.android_test.art_details.ui.ArtDetailsScreen
import com.example.android_test.museum.MainViewModel
import com.example.android_test.museum.ui.MuseumScreen

@Composable
fun AppNavigation() {
    val mainViewModel: MainViewModel = hiltViewModel()
    val detailsViewModel: ArtDetailsViewModel = hiltViewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "museum") {
        composable("museum") { MuseumScreen(navController = navController, mainViewModel) }
        composable("details/{itemId}") { backStackEntry ->
            ArtDetailsScreen(
                navController = navController,
                detailsViewModel,
                itemId = backStackEntry.arguments?.getString("itemId")
            )
        }
        // Add more composable destinations as needed
    }
}
