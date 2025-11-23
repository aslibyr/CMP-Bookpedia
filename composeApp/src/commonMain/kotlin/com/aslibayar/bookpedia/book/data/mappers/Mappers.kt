package com.aslibayar.bookpedia.book.data.mappers

import com.aslibayar.bookpedia.book.data.dto.SearchedBookDto
import com.aslibayar.bookpedia.book.domain.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id,
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/id/$coverKey-L.jpg"
        } else if (coverAlternativeKey != null) {
            "https://covers.openlibrary.org/b/olid/$coverAlternativeKey-L.jpg"
        } else {
            ""
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = this.language ?: emptyList(),
        publishYear = this.firstPublishYear.toString(),
        avgRating = null,
        ratingCount = null,
        pages = this.numberOfPagesMedian,
        numEditions = this.editionCount ?: 0
    )
}