package com.example.whacamole.screens.results.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.whacamole.ui.theme.GreenDarkPrimary

@Composable
fun ScoreCard(
    text: String,
    score: String
) {
    Card(shape = RoundedCornerShape(8.dp),
    backgroundColor = MaterialTheme.colors.surface,
    border = BorderStroke(4.dp, GreenDarkPrimary)
    ) {
        Column(modifier = Modifier.padding(16.dp).width(140.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = score, fontSize = 24.sp)
        }
    }
}