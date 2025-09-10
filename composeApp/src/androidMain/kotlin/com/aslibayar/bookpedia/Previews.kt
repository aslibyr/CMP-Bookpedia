package com.aslibayar.bookpedia

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aslibayar.bookpedia.book.presentation.book_list.BookListScreen
import com.aslibayar.bookpedia.book.presentation.book_list.BookListState
import com.aslibayar.bookpedia.book.presentation.book_list.books
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


@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(state = BookListState(searchResults = books), onAction = {})
}