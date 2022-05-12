package com.example.whacamole.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Navigator {
    private val _destination: MutableStateFlow<NavigationDestinations> = MutableStateFlow(NavigationDestinations.MenuScreen)
    val destination: StateFlow<NavigationDestinations> = _destination

    fun navigate(destination: NavigationDestinations) {
        this._destination.value = destination
    }
}