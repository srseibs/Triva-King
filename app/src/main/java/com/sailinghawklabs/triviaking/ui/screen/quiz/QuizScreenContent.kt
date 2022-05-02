package com.sailinghawklabs.triviaking.ui.screen.quiz

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.domain.model.Question
import com.sailinghawklabs.triviaking.ui.theme.LocalDimensions
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreenContent(
    viewState: QuizScreenState,
) {

    Scaffold(
        topBar = {
            QuizToolbar(title = "Trivia King")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)  // for the Scaffold top bar
                .navigationBarsPadding() // for the bottom buttons
                .padding(20.dp)
        ) {
            QuestionSection(viewState.question.question)
            Spacer(modifier = Modifier.height(10.dp))
            AnswerSection(
                answers = viewState.question.answers,
                onAnswerClicked = {})
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionSection(question: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Question:",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
        )

        Text(
            text = question,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AnswerSection(
    answers: List<String>,
    onAnswerClicked: (Int) -> Any,
) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        answers.forEach {
            AnswerCard(
                answerText = it,
                onClicked = {},
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerCard(
    answerText: String,
    onClicked: () -> Any,
) {
    ElevatedCard(
        colors = elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        onClick = {},
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = answerText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
private fun QuizToolbar(
    title: String,
) {
    val dimensions = LocalDimensions.current
    Surface(
        color = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .statusBarsPadding()
                .height(dimensions.toolbarHeight),

            ) {

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
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
fun QuizScreenContentPreview() {

    val question = Question(
        category = "Birds",
        difficulty = "Easy",
        question = "What is the air speed of a swallow",
        correctAnswer = 0,
        answers = listOf(
            "Fully laden?",
            "0.9c",
            "They cannot fly"
        )
    )
    val viewState = QuizScreenState(
        question = question,
        selectedAnswer = 2,
    )

    TriviaKingTheme() {
        QuizScreenContent(viewState = viewState)
    }
}
