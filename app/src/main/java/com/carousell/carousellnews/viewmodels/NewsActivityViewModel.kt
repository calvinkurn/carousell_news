package com.carousell.carousellnews.viewmodels

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
import kotlinx.coroutines.launch

class NewsActivityViewModel(
    private val googleApiRepository: GoogleApiRepository = GoogleApiRepository
) : ViewModel() {
    private val _data = MutableStateFlow<List<NewsDataModel>>(listOf())
    val data get() = _data

    private val _uiState = MutableStateFlow<NewsState>(NewsUiState.InitialState)
    val uiState get() = _uiState

    private var currentSortMode: NewsSortBy? = null

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

    private fun sortData(sortedMode: NewsSortBy) {
        when (sortedMode) {
            is SortByTimeCreated -> {
                sortRecent()
            }

            is SortByRank -> {
                sortPopular()
            }
        }
    }

    private fun fetchData() = viewModelScope.launch {
        googleApiRepository.fetchData(
            onSuccess = {
                it?.let { resultData ->
                    sortRecent(resultData)
                    _uiState.tryEmit(NewsUiState.FetchComplete)
                }
            },
            onError = {
                _uiState.tryEmit(NewsUiState.FetchFailed)
            }
        )
    }

    private fun sortRecent(dataParam: List<NewsDataModel>? = null) {
        if (currentSortMode == SortByTimeCreated) return
        if (dataParam.isNullOrEmpty()) {
            _data.value.sortedByDescending { it.timeCreated }.also {
                _data.tryEmit(it)
            }
        } else {
            dataParam.sortedByDescending { it.timeCreated }.also {
                _data.tryEmit(it)
            }
        }
        currentSortMode = SortByTimeCreated
    }

    private fun sortPopular() {
        if (currentSortMode == SortByRank) return
        _data.value.sortedWith(compareBy<NewsDataModel> { it.rank }.thenByDescending { it.timeCreated })
            .also {
                _data.tryEmit(it)
            }
        currentSortMode = SortByRank
    }
}