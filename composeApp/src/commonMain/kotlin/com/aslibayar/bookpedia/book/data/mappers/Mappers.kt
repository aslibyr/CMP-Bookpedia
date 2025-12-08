package com.aslibayar.bookpedia.book.data.mappers

import com.aslibayar.bookpedia.book.data.dto.SearchedBookDto
import com.aslibayar.bookpedia.book.domain.Book

private fun buildOpenLibraryCoverUrl(key: String?): String {
    if (key.isNullOrBlank()) return ""
    val isOlid = key.startsWith("OL", ignoreCase = true)
    val base =
        if (isOlid) "https://covers.openlibrary.org/b/olid/" else "https://covers.openlibrary.org/b/id/"
    return "$base$key-L.jpg"
}

fun SearchedBookDto.toBook(): Book {
    val resolvedImageUrl =
        buildOpenLibraryCoverUrl(coverKey)
            .ifEmpty { buildOpenLibraryCoverUrl(coverAlternativeKey?.toString()) }

    return Book(
        id = id,
        title = title,
        imageUrl = resolvedImageUrl,
        authors = authorNames ?: emptyList(),
        description = null,
        languages = language ?: emptyList(),
        publishYear = firstPublishYear?.toString(),
        avgRating = null,
        ratingCount = null,
        pages = numberOfPagesMedian,
        numEditions = editionCount ?: 0
    )
}