package com.example.whacamole.screens.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.game.buildGame
import com.example.domain.result.GameEvent
import com.example.domain.usecase.game.GetGameEventUseCase
import com.example.domain.usecase.game.StartGameUseCase
import com.example.domain.usecase.game.WhacUseCase
import com.example.whacamole.navigation.NavigationDestinations
import com.example.whacamole.navigation.Navigator
import com.example.whacamole.screens.game.views.mole.MoleViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getGameEventUseCase: GetGameEventUseCase,
    private val whacUseCase: WhacUseCase,
    private val startGameUseCase: StartGameUseCase
) : ViewModel() {

    private val _gameScreenState = mutableStateOf<GameScreenState>(GameScreenState.EnterState)
    val gameScreenState: State<GameScreenState> = _gameScreenState

    private var rows = 0
    private var cols = 0

    init {
        startGame()
    }

    private fun startGame() {
        viewModelScope.launch {
            getGameResults()
        }

        val gameBuilder = buildGame {
            setColumns(3)
            setRows(3)
            setTime(3f)
            setSpawnTime(0.5f)
        }

        rows = gameBuilder.rows
        cols = gameBuilder.cols

        startGameUseCase.execute(gameBuilder)
    }

    private suspend fun getGameResults() {
        getGameEventUseCase.execute().collect { result ->
            when (result) {
                is GameEvent.GameStart -> {
                    rows = result.gameSettings.rows
                    cols = result.gameSettings.cols
                    _gameScreenState.value =
                        GameScreenState.EmptyField(rows, cols)
                }
                is GameEvent.MoleShow -> {
                    _gameScreenState.value =
                        GameScreenState.HasMole(rows, cols, result.x, result.y, MoleViewState.Show)
                }
                is GameEvent.MoleHide -> {
                    _gameScreenState.value =
                        GameScreenState.HasMole(rows, cols, result.x, result.y, MoleViewState.None)
                }
                is GameEvent.MoleWhac -> {
                    _gameScreenState.value =
                        GameScreenState.HasMole(rows, cols, result.x, result.y, MoleViewState.Whac)
                }
                is GameEvent.GameEnd -> {
                    goToResults()
                }
                is GameEvent.Waiting -> {
                    _gameScreenState.value = GameScreenState.Waiting(result.secondsToStart)
                }
            }
        }
    }

    private fun goToResults() {
        navigator.navigate(NavigationDestinations.ResultsScreen)
    }

    fun clickMole(x: Int, y: Int) {
        whacUseCase.execute(x, y)
    }

}