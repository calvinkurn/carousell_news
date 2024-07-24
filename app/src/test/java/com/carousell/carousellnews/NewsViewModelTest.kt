package com.carousell.carousellnews

import com.carousell.carousellnews.domain.models.NewsDataModel
import com.carousell.carousellnews.domain.repository.GoogleApiRepository
import com.carousell.carousellnews.ui.NewsUiAction
import com.carousell.carousellnews.ui.SortByRank
import com.carousell.carousellnews.viewmodels.NewsActivityViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    private lateinit var viewModel: NewsActivityViewModel
    private val googleApiRepo = mockk<GoogleApiRepository>(relaxed = true)

    val operationSlot = slot<(List<NewsDataModel>?) -> Unit>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        viewModel = NewsActivityViewModel(googleApiRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should trigger fetch data from googleApi`() = runTest(coroutineTestRule.testDispatcher) {
        val data = listOf(
            NewsDataModel("id123","test title","","",100L,"1")
        )

        coEvery { googleApiRepo.fetchData(any(), capture(operationSlot)) } answers {
            operationSlot.captured(data)
        }
        viewModel.setAction(NewsUiAction.FetchData)

        delay(500)

        coVerify { googleApiRepo.fetchData(any(), any()) }
        Assert.assertEquals(data, viewModel.data.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should sort data by created date descending`() = runTest(coroutineTestRule.testDispatcher) {
        val data = getDataSample()

        coEvery { googleApiRepo.fetchData(any(), capture(operationSlot)) } answers {
            operationSlot.captured(data)
        }
        viewModel.setAction(NewsUiAction.FetchData)

        delay(500)

        Assert.assertEquals(data[3].id, viewModel.data.value.first().id)
        Assert.assertEquals(data.size, viewModel.data.value.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should sort data by rank ascending`() = runTest(coroutineTestRule.testDispatcher) {
        val data = getDataSample()

        coEvery { googleApiRepo.fetchData(any(), capture(operationSlot)) } answers {
            operationSlot.captured(data)
        }
        viewModel.setAction(NewsUiAction.FetchData)
        delay(500)
        viewModel.setAction(NewsUiAction.SortData(SortByRank))
        delay(500)

        Assert.assertEquals(data[2].id, viewModel.data.value.first().id)
        Assert.assertEquals(data.size, viewModel.data.value.size)
    }

    private fun getDataSample(): List<NewsDataModel> {
        return listOf(
            NewsDataModel("id123","test title","","",100L,"1"),
            NewsDataModel("id222","test title","","",200L,"3"),
            NewsDataModel("id333","test title","","",300L,"1"),
            NewsDataModel("id444","test title","","",900L,"2")
        )
    }
}