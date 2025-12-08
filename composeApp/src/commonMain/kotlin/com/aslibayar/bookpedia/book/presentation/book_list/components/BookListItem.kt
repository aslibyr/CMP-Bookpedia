package com.aslibayar.bookpedia.book.presentation.book_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aslibayar.bookpedia.book.domain.Book
import com.aslibayar.bookpedia.core.presentation.Lilac
import com.aslibayar.bookpedia.core.presentation.SandYellow
import kotlin.math.round

@Composable
fun BookListItem(
    book: Book, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier.clickable(onClick = onClick),
        color = Lilac.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = book.title,
                modifier = Modifier
                    .height(100.dp)
                    .width(70.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = book.title,
                    maxLines = 2,
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis
                )
                book.authors.firstOrNull()?.let { author ->
                    Text(
                        text = author,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                book.avgRating?.let { rating ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "${round(rating * 10) / 10.0}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = SandYellow
                        )
                    }

                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically).size(32.dp)
            )
        }
    }
}