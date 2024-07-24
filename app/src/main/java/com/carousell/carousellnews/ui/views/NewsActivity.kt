package com.carousell.carousellnews.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.carousell.carousellnews.R
import com.carousell.carousellnews.ui.NewsSortBy
import com.carousell.carousellnews.ui.NewsUiAction
import com.carousell.carousellnews.ui.SortByRank
import com.carousell.carousellnews.ui.SortByTimeCreated
import com.carousell.carousellnews.viewmodels.NewsActivityViewModel
import com.carousell.carousellnewscard.views.RobotoText

class NewsActivity : ComponentActivity() {
    private val viewModel: NewsActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarColors(
                            containerColor = Color(getColor(R.color.toolbar_color)),
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            scrolledContainerColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        title = {
                            RobotoText(
                                color = Color.White,
                                text = getString(R.string.toolbar_title),
                                fontSize = 16f
                            )
                        },
                        actions = {
                            SortViewComponent(
                                onSortSelected = {
                                    sortData(it)
                                },
                                listOfItem = listOf(
                                    Pair(
                                        getString(R.string.toolbar_item_recent),
                                        SortByTimeCreated
                                    ),
                                    Pair(getString(R.string.toolbar_item_popular), SortByRank)
                                )
                            )
                        })
                },
                content = { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding()),
                        color = Color.Gray
                    ) {
                        NewsView(viewModel = viewModel)
                    }
                }
            )
        }

        fetchData()
    }

    private fun fetchData() {
        viewModel.setAction(NewsUiAction.FetchData)
    }

    private fun sortData(sortBy: NewsSortBy) {
        viewModel.setAction(NewsUiAction.SortData(sortBy))
    }
}