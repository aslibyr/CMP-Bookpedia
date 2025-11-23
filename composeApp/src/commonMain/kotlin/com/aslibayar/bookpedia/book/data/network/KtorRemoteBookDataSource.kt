package com.aslibayar.bookpedia.book.data.network

import com.aslibayar.bookpedia.book.data.dto.SearchResponseDto
import com.aslibayar.bookpedia.core.data.safeCall
import com.aslibayar.bookpedia.core.domain.DataError
import com.aslibayar.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource(private val httpClient: HttpClient) : RemoteBookDataSource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString =
                "$BASE_URL/search.json"
            )
            {
                parameter("q", query)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,author_name,author_key,cover_edition_key,cover_i,first_publish_year,rating_average,rating_counts,number_of_pages_median,edition_count"
                )
                parameter("limit", resultLimit)
            }
        }
    }
}