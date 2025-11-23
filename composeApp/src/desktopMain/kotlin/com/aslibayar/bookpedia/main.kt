package com.aslibayar.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP-Bookpedia",
    ) {
        App(engine = io.ktor.client.engine.okhttp.OkHttp.create())
    }
}