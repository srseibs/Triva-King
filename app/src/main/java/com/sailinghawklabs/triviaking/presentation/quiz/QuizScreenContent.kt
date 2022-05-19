package com.sailinghawklabs.triviaking.presentation.quiz

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.data.mapper.toDisplayString
import com.sailinghawklabs.triviaking.data.remote.dto.expandHtmlCodes
import com.sailinghawklabs.triviaking.domain.util.fakeQuestion
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme
import com.sailinghawklabs.triviaking.ui.theme.util.TriBox
import com.sailinghawklabs.triviaking.ui.theme.util.TriBoxState

@Composable
fun QuizScreenContent(
    viewState: QuizScreenState,
    onViewEvent: (QuizScreenEvent) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            QuizTopBar(
                title = "Trivia King",
                onBackPressed = onBackPressed,
            )
        },
        bottomBar = {
            QuizBottomBar(
                questionNumber = viewState.questionNumberDisplay,
                numberCorrect = viewState.numCorrect,
                totalQuestions = viewState.numberOfQuestions,
                category = viewState.category,
                difficulty = viewState.difficulty
            )
        }
    ) { paddingValues ->
        Log.d("pad", "QuizScreenContent: $paddingValues")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)  // for the Scaffold top bar
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (viewState.answers.isNotEmpty()) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    QuestionSection(viewState.question)
                    Spacer(modifier = Modifier.weight(0.5f))
                    AnswerSection(
                        answers = viewState.answers,
                        answerState = viewState.answerBoxes,
                        answersEnabled = viewState.answersEnabled,
                        onAnswerClicked = {
                            onViewEvent(QuizScreenEvent.AnswerPressed(it))
                        }
                    )
                    Spacer(modifier = Modifier.weight(0.5f))
                    ContinuationSection(
                        continueEnabled = viewState.continueEnabled,
                        gameOverEnabled = viewState.gameOverEnabled,
                        onContinueClicked = {
                            onViewEvent(QuizScreenEvent.GetNextQuestion)
                        },
                        onRetryClicked = {
                            onViewEvent(QuizScreenEvent.RetakeThisQuiz)
                        },
                        onNewQuizClicked = {
                            onViewEvent(QuizScreenEvent.CreateNewQuiz)
                        },
                    )
                }
            }
        }

    }
}

@Composable
fun QuestionSection(question: String) {

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {

        val textColor = MaterialTheme.colorScheme.onSurface
        Text(
            text = expandHtmlCodes(question),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = textColor,
        )
    }
}

@Composable
fun ContinuationSection(
    continueEnabled: Boolean,
    gameOverEnabled: Boolean,
    onContinueClicked: () -> Unit,
    onRetryClicked: () -> Unit,
    onNewQuizClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .alpha(if (continueEnabled || gameOverEnabled) 1f else 0f)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = CenterVertically,

            ) {
            if (gameOverEnabled) {
                QuizBottomButton(
                    onClick = onRetryClicked,
                    enabled = gameOverEnabled,
                    label = "Retry Quiz"
                )

                QuizBottomButton(
                    onClick = onNewQuizClicked,
                    enabled =  gameOverEnabled,
                    label = "New Quiz",
                )

            } else {
                QuizBottomButton(
                    onClick = onContinueClicked,
                    enabled = continueEnabled,
                    label = "Next Question"
                )
            }

        }
    }
}

@Composable
fun QuizBottomButton(
    label: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick =  onClick,
        enabled = enabled,
        colors = buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun AnswerSection(
    answers: List<String>,
    answersEnabled: Boolean,
    answerState: List<TriBoxState>,
    onAnswerClicked: (Int) -> Unit,
) {

    Log.d("", "AnswerSection: ${answerState.joinToString()}")
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        answers.forEachIndexed { i, it ->
            AnswerDisplay(
                answerText = expandHtmlCodes(it),
                answerState = answerState[i],
                enabled = answersEnabled,
                onClicked = {
                    onAnswerClicked(i)
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerDisplay(
    answerText: String,
    answerState: TriBoxState,
    enabled: Boolean,
    onClicked: () -> Any,
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        TriBox(
            state = answerState,
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    enabled = enabled,
                ) {
                    onClicked()
                }
        )

        ElevatedCard(
            colors = elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            enabled = enabled,
            onClick = { onClicked() },
            modifier = Modifier
                .padding(start = 2.dp)
                .fillMaxWidth(),
        ) {

            Text(
                text = answerText,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(10.dp)
            )
        }

    }


}

@Composable
private fun QuizTopBar(
    title: String,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        navigationIcon = {
                         IconButton(onClick = onBackPressed ) {
                             Icon(
                                 imageVector = Icons.Default.ArrowBack,
                                 tint = MaterialTheme.colorScheme.onPrimary,
                                 contentDescription = "back" )
                         }
        },
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
    showSystemUi = true,
    device = Devices.PIXEL
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true,
    device = Devices.PIXEL
)
@Composable
fun QuizScreenContentPreview() {

    val dummyQuestion = fakeQuestion()
    val viewState = QuizScreenState(
        question = dummyQuestion.question,
        category = dummyQuestion.category,
        difficulty = dummyQuestion.difficulty.toDisplayString(),
        answers = dummyQuestion.answers,
        answerBoxes = listOf(
            TriBoxState.CORRECT, TriBoxState.UNCHECKED, TriBoxState.WRONG
        ),
        questionNumberDisplay = 1,
        numberOfQuestions = 5,
        numCorrect = 1,
        continueEnabled = false,
        gameOverEnabled = true,
    )
    TriviaKingTheme() {
        QuizScreenContent(
            viewState = viewState,
            onViewEvent = {},
            onBackPressed = {},
        )
    }
}
