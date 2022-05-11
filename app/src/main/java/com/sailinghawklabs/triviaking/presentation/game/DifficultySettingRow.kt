package com.sailinghawklabs.triviaking.presentation.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.data.mapper.toDisplayString
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultySettingRow(
    difficulty: DIFFICULTY,
    onChanged: (DIFFICULTY) -> Unit,
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
            .fillMaxWidth(),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            DIFFICULTY.values().forEach {
                DifficultyButton(
                    label = it.toDisplayString(),
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