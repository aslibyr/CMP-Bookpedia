package com.aslibayar.bookpedia.book.domain

data class Book(
    val id: String,
    val title: String,
    val imageUrl: String,
    val authors: List<String>,
    val description: String?,
    val languages: List<String>,
    val publishYear: String?,
    val avgRating: Double?,
    val ratingCount: Int?,
    val pages: Int?,
    val numEditions: Int
)
