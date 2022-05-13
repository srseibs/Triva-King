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
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreenContent(
    viewState: QuizScreenState,
    onViewEvent: (QuizScreenEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            QuizTopBar(title = "Trivia King")
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
                        buttonEnabled = viewState.continueEnabled,
                        buttonLabel = viewState.continueLabel,
                        onClicked = {
                            onViewEvent(QuizScreenEvent.NextQuestionPressed)
                        }
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
    buttonLabel: String,
    buttonEnabled: Boolean,
    onClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .alpha(if (buttonEnabled) 1f else 0f)
            .fillMaxWidth(),
    ) {
        Button(
            onClick = { onClicked() },
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = buttonLabel,
                style = MaterialTheme.typography.titleLarge
            )
        }
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
        verticalAlignment = Alignment.CenterVertically,
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
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )
        }
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
    )
    TriviaKingTheme() {
        QuizScreenContent(
            viewState = viewState,
            onViewEvent = {},
        )
    }
}
