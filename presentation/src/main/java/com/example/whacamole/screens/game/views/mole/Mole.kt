package com.example.whacamole.screens.game.views.mole

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun Mole(
    moleViewState: MoleViewState
) {
    var color = Color(0xFF7d510f)
    when(moleViewState) {
        MoleViewState.Show -> {
            color = Color(0xFF7d510f)
        }
        MoleViewState.Whac -> {
            color = Color(0xFF06bd14)
        }
        MoleViewState.None -> return
    }

    Canvas(modifier = Modifier.fillMaxWidth().aspectRatio(1f), onDraw = {
        drawCircle(
            color = color,
            center = Offset(x = size.width / 2, y = size.height / 2),
            radius = size.minDimension / 5
        )
    })

}