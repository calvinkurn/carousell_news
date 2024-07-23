package com.carousell.carousellnews.ui

interface NewsState

object NewsUiAction {
    object FetchData: NewsState
    data class SortData(val targetSort: NewsSortBy): NewsState
}

object NewsUiState {
    object InitialState: NewsState
    object FetchComplete: NewsState
    object FetchFailed: NewsState
}

interface NewsSortBy
object SortByTimeCreated: NewsSortBy
object SortByRank: NewsSortBy