package com.sailinghawklabs.triviaking.ui.theme.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import com.sailinghawklabs.triviaking.ui.theme.AppGreenPass
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme


sealed class TriBoxState {
    object UNCHECKED : TriBoxState()
    object CORRECT : TriBoxState()
    object WRONG : TriBoxState()
}

fun TriBoxState.toToggleableState(): ToggleableState {
    return when (this) {
        is TriBoxState.UNCHECKED -> ToggleableState.Off
        is TriBoxState.CORRECT -> ToggleableState.On
        is TriBoxState.WRONG -> ToggleableState.Indeterminate
    }
}

@Composable
fun TriBox(
    state: TriBoxState,
    modifier: Modifier = Modifier,
) {

    when (state) {
        is TriBoxState.UNCHECKED -> {
            Icon(
                imageVector = Icons.Filled.CheckBoxOutlineBlank,
                contentDescription = "unselected",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = modifier,
            )
        }
        is TriBoxState.CORRECT -> {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "correct",
                tint = MaterialTheme.colorScheme.AppGreenPass,
                modifier = modifier,
            )
        }
        is TriBoxState.WRONG -> {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "wrong",
                tint = Color.Red,
                modifier = modifier,
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
fun TriBoxPreview() {

    TriviaKingTheme {
        Column {
            TriBox(state = TriBoxState.CORRECT)
            TriBox(state = TriBoxState.WRONG)
            TriBox(state = TriBoxState.UNCHECKED)
        }

    }
}