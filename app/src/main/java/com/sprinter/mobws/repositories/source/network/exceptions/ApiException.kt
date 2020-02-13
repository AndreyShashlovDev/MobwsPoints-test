package com.sprinter.mobws.repositories.source.network.exceptions

data class ApiException(private val errorCode: Int, private val errorMessage: String) :
    RuntimeException("$errorCode: $errorMessage")
