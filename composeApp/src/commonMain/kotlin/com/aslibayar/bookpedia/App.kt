package com.aslibayar.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aslibayar.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.aslibayar.bookpedia.book.data.repository.DefaultBookRepository
import com.aslibayar.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.aslibayar.bookpedia.book.presentation.book_list.BookListViewModel
import com.aslibayar.bookpedia.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine

@Composable
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember {
            BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory().create(engine)
                    )
                )
            )
        },
        onBookClicked = {}
    )
}