package com.carousell.carousellnews.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.carousell.carousellnews.ui.NewsSortBy

@Composable
fun SortViewComponent(
    onSortSelected: (sortMode: NewsSortBy) -> Unit = {},
    listOfItem: List<Pair<String, NewsSortBy>> = listOf()
) {
    var expandState by remember {
        mutableStateOf(false)
    }

    Column {
        IconButton(
            modifier = Modifier.testTag("Sort Button"),
            onClick = {
                expandState = !expandState
            }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Sort option"
            )
        }
        DropdownMenu(
            expanded = expandState,
            onDismissRequest = { expandState = false },
        ) {
            listOfItem.forEach { (text, actionItem) ->
                DropdownMenuItem(
                    text = { Text(text = text) },
                    onClick = {
                        onSortSelected(actionItem)
                        expandState = !expandState
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun SortViewComponentPreview() {
    SortViewComponent()
}