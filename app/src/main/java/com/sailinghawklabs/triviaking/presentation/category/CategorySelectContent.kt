package com.sailinghawklabs.triviaking.presentation.category

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.gameCategoryALL
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectContent(
    viewState: CategorySelectState,
    onCategoryClicked: (Category?) -> Any,
    onBackClicked: () -> Unit,
) {

    Scaffold(
        topBar = {
            ListToolBar(
                title = "Category",
                onBackClicked = { onBackClicked() },
                onCategoryClicked = { onCategoryClicked(it) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)  // for the Scaffold top bar
        ) {
            when (viewState) {
                is CategorySelectState.ListAvailable -> {
                    LazyColumn(
                    ) {
                        items(viewState.categories) { category ->
                            CategoryListItem(
                                category = category,
                                onCategorySelected = { onCategoryClicked(category) },
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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(50.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = viewState.errorMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
                is CategorySelectState.Dismissed -> {


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
    onBackClicked: () -> Unit,
    onCategoryClicked: (Category?) -> Any,
) {

    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        navigationIcon =
        {
            IconButton(
                onClick = {
                    onBackClicked()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "back"
                )
            }
        },
        actions = {
            Button(
                onClick = { onCategoryClicked(gameCategoryALL) },
                colors = buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),

            ) {
                Text(
                    text = "Use All",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        title = {

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )

        }
    )
}

class CategorySelectStateProvider : PreviewParameterProvider<CategorySelectState> {
    override val values: Sequence<CategorySelectState>
        get() = sequenceOf(
            CategorySelectState.LoadingState,
            CategorySelectState.Error(
                "sample error message that is long enough to wrap around to multiple lines."
            ),
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
            onBackClicked = {},
            onCategoryClicked = {},
        )
    }
}