package com.aslibayar.bookpedia

import androidx.compose.runtime.Composable
import com.aslibayar.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.aslibayar.bookpedia.book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    BookListScreenRoot(
        viewModel = viewModel,
        onBookClicked = {}
    )
}