package com.example.whacamole.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.whacamole.screens.game.views.Field
import com.example.whacamole.screens.game.views.mole.MoleViewState

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {

    val state by viewModel.gameScreenState

    when (val screenState = state) {
        is GameScreenState.EnterState -> {}

        is GameScreenState.EmptyField -> {
            Field(rows = screenState.rows, columns = screenState.cols)
        }

        is GameScreenState.HasMole -> {
            Field(rows = screenState.rows,
                columns = screenState.cols,
                moleX = screenState.moleX,
                moleY = screenState.moleY,
                moleViewState = screenState.moleViewState,
                holeClick = { x, y ->
                    viewModel.clickMole(x, y)
                })
        }

        is GameScreenState.Waiting -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = screenState.seconds.toString(), fontSize = 36.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}