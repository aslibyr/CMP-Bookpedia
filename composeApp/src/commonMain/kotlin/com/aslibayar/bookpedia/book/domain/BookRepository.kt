package com.aslibayar.bookpedia.book.domain

import com.aslibayar.bookpedia.core.domain.DataError
import com.aslibayar.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}