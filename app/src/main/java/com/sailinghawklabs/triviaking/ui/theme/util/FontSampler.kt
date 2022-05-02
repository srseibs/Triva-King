package com.sailinghawklabs.triviaking.ui.theme.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme

@Composable
fun FontSampler() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn() {
            item {
                FontCard("displayLarge", MaterialTheme.typography.displayLarge)
                FontCard("displayMedium", MaterialTheme.typography.displayMedium)
                FontCard("displaySmall", MaterialTheme.typography.displaySmall)
                FontCard("headlineLarge", MaterialTheme.typography.headlineLarge)
                FontCard("headlineMedium", MaterialTheme.typography.headlineMedium)
                FontCard("headlineSmall", MaterialTheme.typography.headlineSmall)
                FontCard("titleLarge", MaterialTheme.typography.titleLarge)
                FontCard("titleMedium", MaterialTheme.typography.titleMedium)
                FontCard("titleSmall", MaterialTheme.typography.titleSmall)
                FontCard("bodyLarge", MaterialTheme.typography.bodyLarge)
                FontCard("bodyMedium", MaterialTheme.typography.bodyMedium)
                FontCard("bodySmall", MaterialTheme.typography.bodySmall)
                FontCard("labelLarge", MaterialTheme.typography.labelLarge)
                FontCard("labelMedium", MaterialTheme.typography.labelMedium)
                FontCard("labelSmall", MaterialTheme.typography.labelSmall)
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FontCard(
    textStyleName: String,
    textStyle: TextStyle,
) {
    val sampleText = textStyleName.uppercase() +
            ": The quick brown fox crossed the road."

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            text = sampleText,
            style = textStyle,
            modifier = Modifier.padding(10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(
    name = "Night Mode",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun FontSamplerPreview() {

    TriviaKingTheme() {
        FontSampler()
    }
}