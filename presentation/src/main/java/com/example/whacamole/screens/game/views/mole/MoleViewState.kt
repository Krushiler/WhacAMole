package com.example.whacamole.screens.game.views.mole

sealed class MoleViewState {
    object Show: MoleViewState()
    object Whac: MoleViewState()
    object None: MoleViewState()
}
