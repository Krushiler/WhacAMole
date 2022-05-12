package com.example.whacamole.screens.game.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whacamole.screens.game.views.mole.MoleViewState

@Composable
fun Field(
    rows: Int,
    columns: Int,
    moleX: Int,
    moleY: Int,
    moleViewState: MoleViewState,
    holeClick: (x: Int, y: Int) -> Unit
) {
    Row(
        Modifier
            .fillMaxSize()
            .aspectRatio(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
        repeat(rows) { row ->
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
                repeat(columns) { column ->
                    if (moleX == row && moleY == column) {
                        Hole(moleViewState, row, column) { x, y ->
                            holeClick.invoke(x, y)
                        }
                    } else {
                        Hole(MoleViewState.None, x = row, y = column) { x, y ->
                            holeClick.invoke(x, y)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Field(
    rows: Int,
    columns: Int,
) {
    Row(
        Modifier
            .fillMaxSize()
            .aspectRatio(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
        repeat(rows) { row ->
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
                repeat(columns) { column ->
                    Hole(row, column)
                }
            }
        }
    }
}