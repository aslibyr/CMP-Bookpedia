package com.aslibayar.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.aslibayar.bookpedia.app.App
import com.aslibayar.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}