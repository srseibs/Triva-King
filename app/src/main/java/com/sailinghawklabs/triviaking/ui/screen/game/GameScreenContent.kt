package com.sailinghawklabs.triviaking.ui.screen.game

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.sailinghawklabs.triviaking.domain.usecase.GetQuestionSet
import com.sailinghawklabs.triviaking.ui.screen.destinations.CategorySelectScreenDestination
import com.sailinghawklabs.triviaking.ui.theme.LocalDimensions
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
                        onClick = { }
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
        Text(
            text = "(click to change)",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
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
            title = "Category",
            value = viewState.gamePreferences.category?.name ?: "ALL"
        ) {
            navigator.navigate(CategorySelectScreenDestination)
        }

        DifficultySettingRow(
            difficulty = viewState.gamePreferences.difficulty,
            onChanged = { onScreenEvent(GameScreenEvent.DifficultyChanged(it)) }
        )

        SettingRow(
            title = "Number of Questions",
            value = viewState.gamePreferences.numberOfQuestions.toString()
        ) {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySettingRow(
    title: String,
    value: String,
    onClick: () -> Unit,
) {
    OutlinedCard(
        onClick = onClick,
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 12.dp
        ),
        shape = RoundedCornerShape(percent = 30),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,

            ),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
    ) {

        Row(
            modifier = Modifier
                .padding(20.dp)
                .defaultMinSize(40.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(CenterVertically)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = value,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultySettingRow(
    difficulty: GetQuestionSet.DIFFICULTY,
    onChanged: (GetQuestionSet.DIFFICULTY) -> Unit,
) {
    OutlinedCard(
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 12.dp
        ),
        shape = RoundedCornerShape(percent = 30),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,

            ),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .defaultMinSize(minHeight = 40.dp)
        ) {
            GetQuestionSet.DIFFICULTY.values().forEach {
                DifficultyButton(
                    label = it.name,
                    selected = (difficulty == it),
                    onClicked = { onChanged(it) },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyButton(
    label: String,
    selected: Boolean,
    onClicked: () -> Unit,
) {


    OutlinedCard(
        onClick = { onClicked() },
        shape = RoundedCornerShape(percent = 30),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = if (selected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingRow(
    title: String,
    value: String,
    onClick: () -> Unit,
) {
    OutlinedCard(
        onClick = onClick,
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 12.dp
        ),
        shape = RoundedCornerShape(percent = 30),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,

            ),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
    ) {

        Row(
            modifier = Modifier
                .padding(20.dp)
                .defaultMinSize(40.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(CenterVertically)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = value,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

}

@Composable
private fun GameTopBar(
    title: String,
) {
    val dimensions = LocalDimensions.current

    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Category, contentDescription = "testing")
            }
        }
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
    )

    TriviaKingTheme {

        GameScreenContent(
            viewState = viewState,
            navigator = EmptyDestinationsNavigator,
            onScreenEvent = {},
        )
    }
}