package com.example.whacamole.screens.results

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.whacamole.screens.results.views.ResultScreenScore

@Composable
fun ResultsScreen(viewModel: ResultsViewModel) {

    val state by viewModel.resultsScreenState

    when (val stateValue = state) {
        is ResultsScreenState.Points -> {
            ResultScreenScore(
                recordState = stateValue.recordState,
                score = stateValue.points.toString(),
                restartClick = { viewModel.restartGame() },
                menuClick = { viewModel.goToMenu() }
            )
        }
        ResultsScreenState.Waiting -> {}
    }

}