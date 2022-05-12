package com.example.whacamole.navigation

sealed class NavigationDestinations(val name: String) {
    object MenuScreen: NavigationDestinations("Menu")
    object GameScreen: NavigationDestinations("Game")
    object ResultsScreen: NavigationDestinations("Results")
}
