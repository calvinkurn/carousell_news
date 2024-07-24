package com.carousell.carousellnews.ui.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.carousell.carousellnews.utils.toDateCreationFormat
import com.carousell.carousellnews.viewmodels.NewsActivityViewModel
import com.carousell.carousellnewscard.views.CarousellCard

@Composable
fun NewsView(viewModel: NewsActivityViewModel) {
    val listData = viewModel.data.collectAsState()

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