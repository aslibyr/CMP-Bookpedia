package com.aslibayar.bookpedia.book.data.repository

import com.aslibayar.bookpedia.book.data.mappers.toBook
import com.aslibayar.bookpedia.book.data.network.RemoteBookDataSource
import com.aslibayar.bookpedia.book.domain.Book
import com.aslibayar.bookpedia.book.domain.BookRepository
import com.aslibayar.bookpedia.core.domain.DataError
import com.aslibayar.bookpedia.core.domain.Result
import com.aslibayar.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }
}