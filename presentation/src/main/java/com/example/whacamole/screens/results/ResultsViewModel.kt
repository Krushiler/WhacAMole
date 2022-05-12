package com.example.whacamole.screens.results

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.result.GameState
import com.example.domain.usecase.game.GetGameStateUseCase
import com.example.domain.usecase.plays.GetRecordUseCase
import com.example.domain.usecase.plays.SaveRecordUseCase
import com.example.whacamole.navigation.NavigationDestinations
import com.example.whacamole.navigation.Navigator
import com.example.whacamole.screens.base.RecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getGameStateUseCase: GetGameStateUseCase,
    private val saveRecordUseCase: SaveRecordUseCase,
    private val getRecordUseCase: GetRecordUseCase
): ViewModel() {

    private val _resultsScreenState = mutableStateOf<ResultsScreenState>(ResultsScreenState.Waiting)
    val resultsScreenState: State<ResultsScreenState> = _resultsScreenState

    init {
        loadScore()
    }

    private fun loadScore() {
        viewModelScope.launch {
            var recordState: RecordState = RecordState.None

            viewModelScope.launch(Dispatchers.IO) {
                val score = getRecordUseCase.execute()
                if (score != null) recordState = RecordState.Exists(score)
            }.join()

            viewModelScope.launch {
                getGameStateUseCase.execute().collect { status ->
                    when (status) {
                        is GameState.End -> {
                            _resultsScreenState.value =
                                ResultsScreenState.Points(status.score, recordState)
                            saveRecordUseCase.execute(status.score)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    fun goToMenu() {
        navigator.navigate(NavigationDestinations.MenuScreen)
    }

    fun restartGame() {
        navigator.navigate(NavigationDestinations.GameScreen)
    }

}