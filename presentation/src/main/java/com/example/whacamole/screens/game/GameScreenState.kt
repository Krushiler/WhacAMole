package com.example.whacamole.screens.game

import com.example.whacamole.screens.game.views.mole.MoleViewState

sealed class GameScreenState {
    object EnterState : GameScreenState()
    class EmptyField(val rows: Int, val cols: Int) : GameScreenState()
    class Waiting(val seconds: Int) : GameScreenState()

    class HasMole(
        val rows: Int,
        val cols: Int,
        val moleX: Int,
        val moleY: Int,
        val moleViewState: MoleViewState
    ) : GameScreenState()

}