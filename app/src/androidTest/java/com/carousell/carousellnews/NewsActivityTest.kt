package com.carousell.carousellnews

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.carousell.carousellnews.ui.views.NewsActivity
import org.junit.Rule
import org.junit.Test

class NewsActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(NewsActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSortItem() {
        composeTestRule.onNodeWithTag("Sort Button").performClick()

        composeTestRule.onNodeWithText("Recent").assertExists()
        composeTestRule.onNodeWithText("Popular").assertExists()
    }
}