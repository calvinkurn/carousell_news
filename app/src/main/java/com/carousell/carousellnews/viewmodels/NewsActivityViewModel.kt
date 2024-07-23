package com.carousell.carousellnews.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carousell.carousellnews.domain.models.NewsDataModel
import com.carousell.carousellnews.domain.repository.GoogleApiRepository
import com.carousell.carousellnews.ui.NewsSortBy
import com.carousell.carousellnews.ui.NewsState
import com.carousell.carousellnews.ui.NewsUiAction
import com.carousell.carousellnews.ui.NewsUiState
import com.carousell.carousellnews.ui.SortByRank
import com.carousell.carousellnews.ui.SortByTimeCreated
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsActivityViewModel(
    private val googleApiRepository: GoogleApiRepository = GoogleApiRepository
) : ViewModel() {
    private val _data = MutableStateFlow<List<NewsDataModel>>(listOf())
    val data get() = _data

    private val _uiState = MutableStateFlow<NewsState>(NewsUiState.InitialState)
    val uiState get() = _uiState

    fun setAction(action: NewsState) {
        when (action) {
            is NewsUiAction.FetchData -> {
                fetchData()
            }

            is NewsUiAction.SortData -> {
                sortData(action.targetSort)
            }
        }
    }

    private fun sortData(sortBy: NewsSortBy) {
        when (sortBy) {
            is SortByRank -> {
                _data.value.sortedWith(compareBy<NewsDataModel> { it.rank }.thenBy { it.timeCreated })
                    .also { sortedData ->
                        _data.tryEmit(sortedData)
                    }
            }

            is SortByTimeCreated -> {

            }
        }
    }

    private fun fetchData() = viewModelScope.launch {
        googleApiRepository.fetchData(
            onSuccess = {
                it?.let { resultData ->
                    _data.tryEmit(resultData)
                    _uiState.tryEmit(NewsUiState.FetchComplete)
                }
            },
            onError = {
                _uiState.tryEmit(NewsUiState.FetchFailed)
            }
        )
    }
}