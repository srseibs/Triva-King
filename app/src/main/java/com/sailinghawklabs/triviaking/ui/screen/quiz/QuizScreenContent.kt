package com.sailinghawklabs.triviaking.ui.screen.quiz

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.domain.util.fakeQuestion
import com.sailinghawklabs.triviaking.ui.theme.LocalDimensions
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme
import com.sailinghawklabs.triviaking.ui.theme.util.TriBox
import com.sailinghawklabs.triviaking.ui.theme.util.TriBoxState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreenContent(
    viewState: QuizScreenState,
) {
    Scaffold(
        topBar = {
            TriviaAppBar(title = "Trivia King")
        },
        bottomBar = {
            QuizBottomBar(
                questionNumber = viewState.questionNumber,
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
//                .padding(paddingValues.calculateTopPadding())  // for the Scaffold top bar
                .background(MaterialTheme.colorScheme.surface)
        ){
            if (viewState.answers.isNotEmpty()) {
                Column(
                ) {
                    QuestionSection(viewState.question)
                    Spacer(modifier = Modifier.height(10.dp))
                    AnswerSection(
                        answers = viewState.answers,
                        answerState = viewState.answerState,
                        onAnswerClicked = {}
                    )
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionSection(question: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
    ) {

        val textColor = MaterialTheme.colorScheme.onSurface

        Text(
            text = "Question:",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = textColor,
        )

        Text(
            text = question,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = textColor,
        )
    }
}

@Composable
fun AnswerSection(
    answers: List<String>,
    answerState: List<TriBoxState>,
    onAnswerClicked: (Int) -> Any,
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        answers.forEachIndexed {i, it ->
            AnswerDisplay(
                answerText = it,
                answerState = answerState[i],
                onClicked = {},
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
    onClicked: () -> Any,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        TriBox(
            state = answerState,
            modifier = Modifier.size(32.dp)
        )

        ElevatedCard(
            colors = elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            onClick = {},
            modifier = Modifier
                .padding(start = 2.dp)
                .fillMaxWidth(),
        ) {

            Text(
                text = answerText,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(20.dp)
            )
        }

    }


}

@Composable
private fun TriviaAppBar(
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

@Composable
private fun QuizBottomBar(
    questionNumber: Int,
    totalQuestions: Int,
    numberCorrect: Int,
    category: String,
    difficulty: String,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 56.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val titleStyle = MaterialTheme.typography.titleMedium
            val valueStyle = MaterialTheme.typography.bodyLarge
            Column(
//                modifier = Modifier.weight(0f)
            ) {
                Text(
                    text = "Question",
                    style = titleStyle,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text = "${questionNumber + 1} of $totalQuestions",
                    style = valueStyle,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.width(18.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = difficulty.replaceFirstChar{it.uppercase()},
                    style = titleStyle,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(text = category,
                    style = valueStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.width(18.dp))
            Column(
//                modifier = Modifier.weight(0f)
            ) {
                Text(
                    text = "Score",
                    style = titleStyle,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text = "$numberCorrect",
                    style = valueStyle,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }

        }
    }

}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false,
)
@Composable
fun QuizScreenContentPreview() {

    val dummyQuestion = fakeQuestion()
    val viewState = QuizScreenState(
        question = dummyQuestion.question,
        category = dummyQuestion.category,
        difficulty = dummyQuestion.difficulty,
        answers = dummyQuestion.answers,
        answerState = listOf(
            TriBoxState.CORRECT, TriBoxState.UNCHECKED, TriBoxState.WRONG
        ),
        questionNumber = 1,
        numberOfQuestions = 5,
        numCorrect = 1,
    )
    TriviaKingTheme() {
        QuizScreenContent(viewState = viewState)
    }
}
