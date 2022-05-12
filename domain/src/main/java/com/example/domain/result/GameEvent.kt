package com.example.domain.result

import com.example.domain.game.GameSettings

sealed class GameEvent {
    object GameEnd : GameEvent()
    class GameStart(val gameSettings: GameSettings): GameEvent()
    class MoleShow(val x: Int, val y: Int): GameEvent()
    class MoleHide(val x: Int, val y: Int): GameEvent()
    class MoleWhac(val x: Int, val y: Int): GameEvent()
    class Waiting(val secondsToStart: Int): GameEvent()
}
