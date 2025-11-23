package com.aslibayar.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslibayar.bookpedia.book.domain.Book
import com.aslibayar.bookpedia.book.domain.BookRepository
import com.aslibayar.bookpedia.core.domain.onError
import com.aslibayar.bookpedia.core.domain.onSuccess
import com.aslibayar.bookpedia.core.presentation.toUIText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private var cachedBooks = emptyList<Book>()

    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()


    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClicked -> {

            }

            is BookListAction.OnSearchQueryChanged -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

    private fun observeSearchQuery() {
        state.map { it.searchQuery }.distinctUntilChanged().debounce(500L).onEach { query ->
            when {
                query.isBlank() -> {
                    _state.update {
                        it.copy(
                            errorMessage = null,
                            isLoading = false,
                            searchResults = cachedBooks
                        )
                    }
                }

                query.length >= 2 -> {
                    searchBooks(query)
                }
            }
        }
            .launchIn(viewModelScope)
    }

    private fun searchbooks(query: String) {
        _state.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
        viewModelScope.launch {
            bookRepository.searchBooks(query).onSuccess { searchResults ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = searchResults
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.toUIText()
                    )
                }

            }
        }
    }
}