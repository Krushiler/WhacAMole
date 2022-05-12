package com.example.domain.usecase.game

import com.example.domain.game.GameManager
import com.example.domain.usecase.UseCase

class WhacUseCase(private val gameManager: GameManager): UseCase {
    fun execute(x: Int, y: Int) {
        gameManager.whack(x, y)
    }
}