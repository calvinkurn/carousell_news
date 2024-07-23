package com.carousell.carousellnewscard.views

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.carousell.carousellnewscard.R

object RobotoFontFamily {
    private var robotoFontFamily: FontFamily? = null

    fun getRobotoFontFamily(): FontFamily {
        return robotoFontFamily ?: run {
            FontFamily(
                Font(R.font.roboto_regular, FontWeight.Normal),
                Font(R.font.roboto_medium, FontWeight.Bold)
            ).also {
                robotoFontFamily = it
            }
        }
    }
}