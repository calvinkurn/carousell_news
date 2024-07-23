package com.carousell.carousellnewscard.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.carousell.carousellnewscard.R

@Composable
fun RobotoText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = colorResource(id = R.color.text_color_main),
    fontSize: Float = 12f,
    maxLines: Int = 2
) {
    Text(
        text = text,
        fontFamily = RobotoFontFamily.getRobotoFontFamily(),
        fontWeight = fontWeight,
        modifier = modifier,
        color = color,
        fontSize = TextUnit(fontSize, TextUnitType.Sp),
        maxLines = maxLines
    )
}

@Composable
@Preview
fun RobotoTextPreview() {
    RobotoText(text = "Test", fontWeight = FontWeight.Normal)
}