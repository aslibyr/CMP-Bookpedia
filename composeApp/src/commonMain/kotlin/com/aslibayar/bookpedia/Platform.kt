package com.aslibayar.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform