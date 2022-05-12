package com.example.domain.result

sealed class GameState {
    class End(val score: Int): GameState()
    object Started: GameState()
    object NotStarted: GameState()
}