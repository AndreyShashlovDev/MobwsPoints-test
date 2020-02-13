package com.sprinter.mobws.extensions

import com.sprinter.mobws.repositories.source.network.exceptions.ApiException
import retrofit2.Response
import java.net.HttpURLConnection

fun <T> Response<T>.checkResponse(expectedCode: Int = HttpURLConnection.HTTP_OK): T {
    if (this.code() != expectedCode) {
        throw ApiException(this.code(), this.message())
    }

    return this.body()!!
}
