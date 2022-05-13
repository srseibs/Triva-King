package com.sailinghawklabs.triviaking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.sailinghawklabs.triviaking.presentation.NavGraphs

import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriviaKingTheme {
                ConfigureSystemBars(MaterialTheme.colorScheme.primary)

                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

    @Composable
    private fun ConfigureSystemBars(color: Color) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setStatusBarColor(
                color = color,
                darkIcons = useDarkIcons,
            )
        }
    }
}
