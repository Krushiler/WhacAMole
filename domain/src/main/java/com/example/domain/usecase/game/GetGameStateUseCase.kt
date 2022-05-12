package com.example.domain.usecase.game

import com.example.domain.game.GameManager
import com.example.domain.result.GameState
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.StateFlow

class GetGameStateUseCase(private val gameManager: GameManager): UseCase {
    fun execute(): StateFlow<GameState> = gameManager.gameState()
}