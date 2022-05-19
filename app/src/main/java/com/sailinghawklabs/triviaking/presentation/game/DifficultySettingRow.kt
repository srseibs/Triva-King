package com.sailinghawklabs.triviaking.presentation.game

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.data.mapper.lookupCount
import com.sailinghawklabs.triviaking.data.mapper.toDisplayString
import com.sailinghawklabs.triviaking.domain.model.CategoryStats
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultySettingRow(
    selectedDifficulty: DIFFICULTY,
    onChanged: (DIFFICULTY) -> Unit,
    statCounter: CategoryStats?,
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
            .height(80.dp)
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
                    selected = (selectedDifficulty == it),
                    onClicked = { onChanged(it) },
                    count = it.lookupCount(statCounter),
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
    count: Int?,
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
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                color = if (selected)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp),
            )
            Text(
                text = if (count != null) "($count)" else "",
                style = MaterialTheme.typography.bodySmall,
                color = if (selected)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.align(CenterHorizontally).padding(bottom = 8.dp)
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
fun DifficultySettingRowPreview() {

    val stats = CategoryStats(
        12,44,66,88
    )


    DifficultySettingRow(
        selectedDifficulty = DIFFICULTY.MEDIUM, onChanged = {}, statCounter = stats)

}