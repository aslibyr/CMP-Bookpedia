package com.aslibayar.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_disk_full
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.error_serialization
import cmp_bookpedia.composeapp.generated.resources.error_server_error
import cmp_bookpedia.composeapp.generated.resources.error_too_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown
import com.aslibayar.bookpedia.core.domain.DataError

fun DataError.toUIText(): UiText {
    val stringRes = when (this) {
        is DataError.Remote -> {
            when (this) {
                DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
                DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
                DataError.Remote.SERVER_ERROR -> Res.string.error_server_error
                DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
                DataError.Remote.SERVER -> Res.string.error_unknown
                DataError.Remote.UNKNOWN -> Res.string.error_unknown
                DataError.Remote.SERIALIZATION -> Res.string.error_serialization
            }
        }

        is DataError.Local -> {
            when (this) {
                DataError.Local.DDISK_FULL -> Res.string.error_disk_full
                DataError.Local.UNKNOWN -> Res.string.error_unknown
            }
        }
    }
    return UiText.StringResourceId(stringRes)
}