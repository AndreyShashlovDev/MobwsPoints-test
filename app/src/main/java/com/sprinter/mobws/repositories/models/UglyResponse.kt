package com.sprinter.mobws.repositories.models

data class UglyResponse<T>(val errorCode: Int, val response: T, val errorMessage: String = "")