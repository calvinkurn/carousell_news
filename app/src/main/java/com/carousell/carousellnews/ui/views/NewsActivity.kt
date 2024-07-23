package com.carousell.carousellnews.ui.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.carousell.carousellnews.domain.repository.GoogleApiRepository
import com.carousell.carousellnews.ui.NewsUiAction
import com.carousell.carousellnews.ui.theme.CarousellNewsTheme
import com.carousell.carousellnews.viewmodels.NewsActivityViewModel
import com.carousell.carousellnewscard.views.CarousellCard
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsActivity : ComponentActivity() {
    val viewModel: NewsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarousellNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
                    NewsView(viewModel = viewModel)
                }
            }
        }

        viewModel.setAction(NewsUiAction.FetchData)
    }
}