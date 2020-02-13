package com.sprinter.mobws.repositories

data class Result<T>(val data: T, val error: Throwable? = null)
