package com.example.whacamole.screens.results.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whacamole.R
import com.example.whacamole.screens.base.RecordState
import com.example.whacamole.ui.theme.GreenDarkPrimary

@Composable
fun ResultScreenScore(
    recordState: RecordState,
    score: String,
    restartClick: () -> Unit,
    menuClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (recordState is RecordState.Exists) {
            ScoreCard(text = stringResource(id = R.string.record), score = recordState.score.toString())
            Spacer(modifier = Modifier.height(64.dp))
        }

        ScoreCard(text = stringResource(id = R.string.score), score = score)

        Spacer(modifier = Modifier.height(64.dp))

        Button(onClick = {
            restartClick.invoke()
        }, modifier = Modifier
            .height(48.dp)
            .width(140.dp)) {
            Text(text = stringResource(id = R.string.restart))
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = {
            menuClick.invoke()
        }, modifier = Modifier
            .height(48.dp)
            .width(140.dp)) {
            Text(text = stringResource(R.string.menu))
        }
    }
}
