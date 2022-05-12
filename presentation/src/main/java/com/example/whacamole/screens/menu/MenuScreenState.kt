package com.example.whacamole.screens.menu

sealed class MenuScreenState {
    class HasRecord(val record: Int): MenuScreenState()
    object NoRecord: MenuScreenState()
}