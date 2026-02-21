package com.aslibayar.bookpedia.book.data.mappers

import com.aslibayar.bookpedia.book.data.dto.SearchedBookDto
import com.aslibayar.bookpedia.book.domain.Book

private const val OPEN_LIBRARY_OLID_BASE = "https://covers.openlibrary.org/b/olid/"
private const val OPEN_LIBRARY_ID_BASE = "https://covers.openlibrary.org/b/id/"

private fun buildOpenLibraryCoverUrl(key: String?): String? {
    val trimmed = key?.trim()
    if (trimmed.isNullOrEmpty()) return null
    val isOlid = trimmed.startsWith("OL", ignoreCase = true)
    val base = if (isOlid) OPEN_LIBRARY_OLID_BASE else OPEN_LIBRARY_ID_BASE
    return "$base$trimmed-L.jpg"
}

fun SearchedBookDto.toBook(): Book {
    val resolvedImageUrl = sequenceOf(
        buildOpenLibraryCoverUrl(coverKey),
        buildOpenLibraryCoverUrl(coverAlternativeKey?.toString())
    ).firstOrNull()

    return Book(
        id = id.substringAfterLast("/", id),
        title = title,
        imageUrl = resolvedImageUrl,
        authors = authorNames.orEmpty(),
        description = null,
        languages = language.orEmpty(),
        publishYear = firstPublishYear?.toString(),
        avgRating = null,
        ratingCount = null,
        pages = numberOfPagesMedian,
        numEditions = editionCount ?: 0
    )
}