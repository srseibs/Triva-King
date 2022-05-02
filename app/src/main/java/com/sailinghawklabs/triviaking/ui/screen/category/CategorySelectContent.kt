package com.sailinghawklabs.triviaking.ui.screen.category

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.ui.theme.LocalDimensions
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectContent(
    viewState: CategorySelectState,
) {

    Scaffold(
        topBar = {
            ListToolBar(title = "Select a Category")
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)  // for the Scaffold top bar
                .navigationBarsPadding() // for the bottom buttons
        ) {
            when (viewState) {
                is CategorySelectState.ListAvailable -> {
                    LazyColumn(
                    ) {
                        items(viewState.categories) { category ->
                            CategoryListItem(
                                category = category,
                                onCategorySelected = { /*TODO*/ },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
                is CategorySelectState.LoadingState -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }

                }
                is CategorySelectState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = viewState.errorMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
                else -> {
                    Log.d("", "CategorySelectContent: Unexpected state...")
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

class CategorySelectStateProvider : PreviewParameterProvider<CategorySelectState> {
    override val values: Sequence<CategorySelectState>
        get() = sequenceOf(
            CategorySelectState.LoadingState,
            CategorySelectState.Error("sample error message"),
            CategorySelectState.ListAvailable(
                (0..10).map {
                    Category(
                        id = it,
                        name = "string $it"
                    )
                })
        )
}


@Preview(
    name = "Night Mode",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CategorySelectContentPreview(
    @PreviewParameter(CategorySelectStateProvider::class) viewState: CategorySelectState,
) {

    TriviaKingTheme {
        CategorySelectContent(
            viewState = viewState,
        )
    }
}