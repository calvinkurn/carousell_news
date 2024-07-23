package com.carousell.carousellnews.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.carousell.carousellnews.ui.NewsUiAction
import com.carousell.carousellnews.ui.SortByRank
import com.carousell.carousellnews.utils.toDateCreationFormat
import com.carousell.carousellnews.viewmodels.NewsActivityViewModel
import com.carousell.carousellnewscard.views.CarousellCard

@Composable
fun NewsView(viewModel: NewsActivityViewModel) {
    val listData = viewModel.data.collectAsState()

    Column {
        Row {
            Button(onClick = {
                viewModel.setAction(NewsUiAction.SortData(SortByRank))
            }) {
                Icon(Icons.Filled.Edit, contentDescription = "Localized description")
            }
        }
        LazyColumn {
            items(listData.value) {
                CarousellCard(
                    title = it.title,
                    desc = it.description,
                    date = it.timeCreated.toDateCreationFormat(),
                    imageUrl = it.bannerUrl
                )
            }
        }
    }


}