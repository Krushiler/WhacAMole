package com.example.whacamole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.whacamole.navigation.NavigationComponent
import com.example.whacamole.navigation.Navigator
import com.example.whacamole.ui.theme.WhacAMoleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhacAMoleTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF92d197)) {
                    NavigationComponent(navigator = navigator)
                }
            }
        }
    }
}