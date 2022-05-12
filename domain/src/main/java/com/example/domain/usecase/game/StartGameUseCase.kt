package com.example.domain.usecase.game

import com.example.domain.game.GameManager
import com.example.domain.game.GameSettings
import com.example.domain.usecase.UseCase

class StartGameUseCase(private val gameManager: GameManager): UseCase {
    fun execute(gameSettings: GameSettings) {
        gameManager.startGame(gameSettings)
    }
}