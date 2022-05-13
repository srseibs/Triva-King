package com.sailinghawklabs.triviaking.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.data.remote.dto.expandHtmlCodes

@Composable
fun QuizBottomBar(
    questionNumber: Int,
    totalQuestions: Int,
    numberCorrect: Int,
    category: String,
    difficulty: String,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
//        modifier = Modifier.padding(bottom = 56.dp)
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
            ) {
                Text(
                    text = "Question",
                    style = titleStyle,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "$questionNumber of $totalQuestions",
                    style = valueStyle,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.width(18.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = difficulty.replaceFirstChar { it.uppercase() },
                    style = titleStyle,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = expandHtmlCodes(category),
                    textAlign = TextAlign.Center,
                    style = valueStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.width(18.dp))
            Column(
            ) {
                Text(
                    text = "Score",
                    style = titleStyle,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "$numberCorrect",
                    style = valueStyle,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
    }

}