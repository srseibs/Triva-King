package com.sailinghawklabs.triviaking.ui.screen.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        ) {
            QuestionSection(viewState.question.question)
            Spacer(modifier = Modifier.height(10.dp))
            AnswerSection(viewState.question.answers)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionSection(question: String) {

    Card {
        Text(text = question)
    }
}

@Composable
fun AnswerSection(answers: List<String>) {
    Column() {
        answers.forEach {
            Text(text = it)
        }
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
    showSystemUi = true,
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
        QuizScreenContent(viewState = viewState )
    }
}
