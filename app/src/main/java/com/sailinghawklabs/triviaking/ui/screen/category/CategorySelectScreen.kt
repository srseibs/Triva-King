package com.sailinghawklabs.triviaking.ui.screen.category

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sailinghawklabs.triviaking.ui.theme.LocalDimensions


@Composable
fun CategorySelectScreen(
    viewModel: CategorySelectViewModel = hiltViewModel(),
) {

    val viewState = viewModel.categoryState.collectAsState().value

    Scaffold(
        topBar = {
            ListToolBar(title = "Categories")
        }
    ) {
        if (viewState is CategorySelectState.ListAvailable) {
            LazyColumn(
            ) {
                items(viewState.categories){
                    Text(text = it)
                }
            }
        }
    }


}

@Composable
private fun ListToolBar(
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
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Preview
@Composable
fun TitleBarPreview() {
    ListToolBar(title = "Categories")
}