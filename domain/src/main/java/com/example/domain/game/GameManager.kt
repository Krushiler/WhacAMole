package com.example.domain.game

import android.util.Log
import com.example.domain.game.objects.Field
import com.example.domain.result.GameEvent
import com.example.domain.result.GameState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameManager {

    private val gameEventFlow = MutableSharedFlow<GameEvent>()
    fun gameEvent(): SharedFlow<GameEvent> = gameEventFlow

    private val gameStateFlow = MutableStateFlow<GameState>(GameState.NotStarted)
    fun gameState(): StateFlow<GameState> = gameStateFlow

    private var points = 0

    private var isWhaced = false

    private var spawnerJob: Job? = null
    private var gameJob: Job? = null
    private var setGameResultJob: Job? = null
    private var setGameStatusJob: Job? = null

    private var field = Field(0, 0)

    private var time = 0L
    private var spawnTime = 0L

    fun startGame(gameSettings: GameSettings) {
        setGameState(GameState.Started)

        gameJob?.cancel()
        spawnerJob?.cancel()

        points = 0

        field = Field(gameSettings.rows, gameSettings.cols)

        time = (gameSettings.time * 1000).toLong()
        spawnTime = (gameSettings.spawnTime * 1000).toLong()

        points = 0

        GlobalScope.launch {

            for (i in 3 downTo 1) {
                emitGameEvent(GameEvent.Waiting(i))
                delay(1000)
            }

            emitGameEvent(GameEvent.GameStart(gameSettings))

            delay(1000)

            gameJob = launch {
                delay(time)
                spawnerJob?.cancel()
                setGameState(GameState.End(points))
                emitGameEvent((GameEvent.GameEnd))
            }
            spawnerJob = launch {
                while (true) {
                    delay(spawnTime)
                    isWhaced = false

                    val moleCords = field.spawnMole()
                    emitGameEvent(GameEvent.MoleShow(moleCords.first, moleCords.second))
                    delay(spawnTime)
                    if (field.hasMole()) {
                        val cords = field.hideMole()
                        emitGameEvent(GameEvent.MoleHide(cords.first, cords.second))
                    }
                }
            }
        }
    }

    fun whack(x: Int, y: Int) {
        if (field.hasMole(x, y)) {
            points++
            val cords = field.hideMole()
            emitGameEvent(GameEvent.MoleWhac(cords.first, cords.second))
        }
    }

    private fun emitGameEvent(gameEvent: GameEvent) {
        setGameResultJob = GlobalScope.launch {
            gameEventFlow.emit(gameEvent)
        }
    }

    private fun setGameState(gameState: GameState) {
        setGameStatusJob?.cancel()
        setGameStatusJob = GlobalScope.launch {
            gameStateFlow.emit(gameState)
        }
    }

    fun getScore(): Int = points

}