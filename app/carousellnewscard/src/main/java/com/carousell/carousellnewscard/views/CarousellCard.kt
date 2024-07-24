package com.carousell.carousellnewscard.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.carousell.carousellnewscard.R


@Composable
fun CarousellCard(
    title: String = "Title",
    desc: String = "Desc",
    date: String = "1 Days ago",
    imageUrl: String = ""
) {
    Box(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(140.dp),
                    model = imageUrl,
                    contentDescription = title
                )
                Column {
                    RobotoText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 12.dp, 16.dp, 0.dp),
                        text = title,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(
                            id = R.color.text_color_main
                        ),
                        fontSize = 16f
                    )
                    RobotoText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 4.dp, 16.dp, 0.dp),
                        text = desc,
                        color = colorResource(
                            id = R.color.text_color_main
                        ),
                        fontSize = 14f
                    )
                }
                RobotoText(
                    modifier = Modifier.padding(16.dp, 8.dp),
                    text = date,
                    color = colorResource(
                        id = R.color.text_color_secondary
                    ),
                    fontSize = 12f
                )
            }
        }
    }
}

@Composable
@Preview
private fun CarousellCardPreview() {
    CarousellCard()
}