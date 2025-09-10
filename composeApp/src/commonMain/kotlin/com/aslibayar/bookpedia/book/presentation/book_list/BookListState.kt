package com.aslibayar.bookpedia.book.presentation.book_list

import com.aslibayar.bookpedia.book.domain.Book
import com.aslibayar.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorText: UiText? = null
)


val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book Title $it",
        imageUrl = "https://test/200",
        authors = listOf("Aslı BAYAR"),
        description = "Book Description",
        languages = emptyList(),
        publishYear = "2023",
        avgRating = 4.5,
        ratingCount = 100,
        pages = 300,
        numEditions = 5
    )
}
