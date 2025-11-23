package com.aslibayar.bookpedia.book.data.network

import com.aslibayar.bookpedia.book.data.dto.SearchResponseDto
import com.aslibayar.bookpedia.core.domain.DataError
import com.aslibayar.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
    // suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, Remote>
}