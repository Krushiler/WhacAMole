package com.example.domain.usecase.game

import com.example.domain.game.GameManager
import com.example.domain.usecase.UseCase

class GetScoreUseCase(private val gameManager: GameManager): UseCase {
    fun execute() = gameManager.getScore()
}