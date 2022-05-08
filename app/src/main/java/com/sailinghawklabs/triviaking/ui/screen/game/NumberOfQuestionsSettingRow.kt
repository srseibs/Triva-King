package com.sailinghawklabs.triviaking.ui.screen.game

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberOfQuestionsSettingRow(
    numQuestions: Int,
    onNumIncrement: () -> Unit,
    onNumDecrement: () -> Unit,
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
            .height(60.dp)
            .fillMaxSize(),
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = "# of Questions",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(10.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                IconButton(
                    onClick = { onNumDecrement() },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = "minus one",
                        modifier = Modifier.fillMaxHeight()

                    )
                }

                Text(
                    text = numQuestions.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 14.dp),
                    )

                IconButton(
                    onClick = { onNumIncrement() },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = "plus one",
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }

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
fun NumberOfQuestionsSettingRowPreview() {

    TriviaKingTheme() {

        NumberOfQuestionsSettingRow(
            numQuestions = 10,
            onNumDecrement = {},
            onNumIncrement = {},

        )

    }

}