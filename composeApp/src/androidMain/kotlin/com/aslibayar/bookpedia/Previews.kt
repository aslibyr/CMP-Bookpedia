package com.aslibayar.bookpedia

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aslibayar.bookpedia.book.domain.Book
import com.aslibayar.bookpedia.book.presentation.book_list.BookListScreen
import com.aslibayar.bookpedia.book.presentation.book_list.BookListState
import com.aslibayar.bookpedia.book.presentation.book_list.components.SearchBar

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun SearchBarPreview(modifier: Modifier = Modifier) {
    SearchBar(
        searchQuery = "",
        onSearchQueryChanged = {},
        onImeSearch = {},
        modifier = modifier.fillMaxWidth()
    )
}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book Title $it",
        authors = listOf("Aslı BAYAR"),
        description = "This is a description of Book $it. ".repeat(10),
        imageUrl = "https://test/200",
        languages = emptyList(),
        publishYear = "2023",
        avgRating = 4.7676,
        ratingCount = 100,
        pages = 300,
        numEditions = 5
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(state = BookListState(searchResults = books), onAction = {})
}