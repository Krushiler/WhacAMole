package com.example.domain.usecase.game

import com.example.domain.game.GameManager
import com.example.domain.result.GameEvent
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.SharedFlow

class GetGameEventUseCase(private val gameManager: GameManager): UseCase {
    fun execute(): SharedFlow<GameEvent> = gameManager.gameEvent()
}