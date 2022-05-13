package com.sailinghawklabs.triviaking.presentation.category

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListItem(
    category: Category,
    onCategorySelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = onCategorySelected,
    ){
        Row(

        ){
            CategoryBadge(
                title = category.name,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = category.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Composable
fun CategoryBadge(
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape,
            )
    ) {
        Text(
            text = title.substring(0,1).uppercase(),
            modifier = Modifier.align(Alignment.Center).align(Alignment.TopCenter),
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )
    }
}


@Preview(
    name = "NIGHT MODE",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "DAY MODE",
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun CategoryListItemPreview() {
    TriviaKingTheme {
        CategoryListItem(
            category = Category(
                id = 1,
                name = "dummy category with a very long name that will wrap to the next line"
            ),
            onCategorySelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}