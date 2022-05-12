package com.example.whacamole.screens.game.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.example.whacamole.screens.game.views.mole.Mole
import com.example.whacamole.screens.game.views.mole.MoleViewState

@Composable
fun Hole(
    moleViewState: MoleViewState,
    x: Int,
    y: Int,
    onClick: (x: Int, y: Int) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        onClick.invoke(x, y)
                    }
                )
            }) {
            drawCircle(
                color = Color.Black,
                center = Offset(x = size.width / 2, y = size.height / 2),
                radius = size.minDimension / 4
            )
        }
        if (moleViewState !is MoleViewState.None) {
            Mole(moleViewState)
        }
    }
}

@Composable
fun Hole(
    x: Int,
    y: Int
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
    {
        drawCircle(
            color = Color.Black,
            center = Offset(x = size.width / 2, y = size.height / 2),
            radius = size.minDimension / 4
        )
    }
}