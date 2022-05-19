package com.sailinghawklabs.triviaking.presentation.game

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.CategoryStats
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.presentation.destinations.CategorySelectScreenDestination
import com.sailinghawklabs.triviaking.presentation.destinations.QuizScreenDestination
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme

@Composable
fun GameScreenContent(
    viewState: GameScreenState,
    navigator: DestinationsNavigator,
    onScreenEvent: (GameScreenEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            GameTopBar(title = "Trivia King")
        },
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)  // for the Scaffold top bar
                .background(MaterialTheme.colorScheme.surface)
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),

                    ) {
                    Button(
                        onClick = { navigator.navigate(QuizScreenDestination) }
                    ) {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = "Start Game",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }

                GameTitleBlock()

                GameSettingsBlock(viewState, navigator, onScreenEvent)

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun GameTitleBlock() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Game Settings",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GameSettingsBlock(
    viewState: GameScreenState,
    navigator: DestinationsNavigator,
    onScreenEvent: (GameScreenEvent) -> Any,
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {

        CategorySettingRow(
            value = viewState.gamePreferences.category?.name ?: "ALL"
        ) {
            navigator.navigate(CategorySelectScreenDestination)
        }

        DifficultySettingRow(
            selectedDifficulty = viewState.gamePreferences.difficulty,
            onChanged = { onScreenEvent(GameScreenEvent.DifficultyChanged(it)) },
            statCounter = viewState.gamePreferences.categoryStats,
        )

        NumberOfQuestionsSettingRow(
            numQuestions = viewState.gamePreferences.numberOfQuestions,
            onNumIncrement = { onScreenEvent(GameScreenEvent.NumQuestionsChanged(1))},
            onNumDecrement = { onScreenEvent(GameScreenEvent.NumQuestionsChanged(-1))},
            )
    }
}

@Composable
private fun GameTopBar(
    title: String,
) {
    CenterAlignedTopAppBar(
        colors = centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )
        },
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun GameScreenContentPreview() {
    val viewState = GameScreenState(
        gamePreferences = GamePreferences(
            numberOfQuestions = 6,
            difficulty = DIFFICULTY.HARD,
            category = Category(
                id = 12,
                name = "Really long name taking 2 lines."
            ),
            categoryStats = CategoryStats(
                numTotal = 144,
                numHard = 12,
                numMedium = 45,
                numEasy = 88,
            )
        )
    )

    TriviaKingTheme {

        GameScreenContent(
            viewState = viewState,
            navigator = EmptyDestinationsNavigator,
            onScreenEvent = {},
        )
    }
}