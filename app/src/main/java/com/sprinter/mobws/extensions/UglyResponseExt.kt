package com.sprinter.mobws.extensions

import com.sprinter.mobws.repositories.models.UglyResponse
import com.sprinter.mobws.repositories.source.network.exceptions.ApiException

fun <T> UglyResponse<T>.checkInternalError(): UglyResponse<T> {
    if (this.errorCode != 0) {
        throw ApiException(this.errorCode, this.errorMessage)
    }

    return this
}
