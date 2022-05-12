package com.example.whacamole.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.whacamole.R
import com.example.whacamole.screens.results.views.ScoreCard

@Composable
fun MenuScreen(viewModel: MenuViewModel) {

    val screenState by viewModel.menuScreenState

    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        AlertDialog(onDismissRequest = { showDialog.value = false },
            title = {
                Text(text = stringResource(id = R.string.info_title))
            },
            text = {
                Text(text = stringResource(id = R.string.info_text))
            },
            buttons = {}
            )
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        horizontalArrangement = Arrangement.End
        ) {
        IconButton(onClick = { showDialog.value = true }, modifier = Modifier
            .width(32.dp)
            .height(32.dp)) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = null
            )
        }
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when(val state = screenState) {
            is MenuScreenState.HasRecord -> {
                ScoreCard(text = stringResource(id = R.string.record), score = state.record.toString())
                Spacer(modifier = Modifier.height(64.dp))
            }
            MenuScreenState.NoRecord -> {}
        }

        Button(
            onClick = {
                viewModel.goToPlayScreen()
            },
            modifier = Modifier
                .width(140.dp)
                .height(48.dp)
            ) {
            Text(text = stringResource(id = R.string.play))
        }
    }
}