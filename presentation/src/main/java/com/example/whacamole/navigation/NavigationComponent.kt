package com.example.whacamole.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whacamole.screens.game.GameScreen
import com.example.whacamole.screens.game.GameViewModel
import com.example.whacamole.screens.menu.MenuScreen
import com.example.whacamole.screens.menu.MenuViewModel
import com.example.whacamole.screens.results.ResultsScreen
import com.example.whacamole.screens.results.ResultsViewModel

@Composable
fun NavigationComponent(navigator: Navigator) {
    val navController = rememberNavController()
    val destination = navigator.destination.collectAsState()

    LaunchedEffect(destination, block = {
        if (navController.currentDestination?.route != destination.value.name) {
            navController.navigate(destination.value.name)
        }
    })

    NavHost(navController, startDestination = destination.value.name) {
        composable(NavigationDestinations.MenuScreen.name) {
            val viewModel: MenuViewModel = hiltViewModel()
            MenuScreen(viewModel)
        }
        composable(NavigationDestinations.GameScreen.name) {
            val viewModel: GameViewModel = hiltViewModel()
            GameScreen(viewModel)
        }
        composable(NavigationDestinations.ResultsScreen.name) {
            val viewModel: ResultsViewModel = hiltViewModel()
            ResultsScreen(viewModel)
        }
    }
}