package com.sprinter.mobws.utils

import androidx.lifecycle.LiveData

class ExceptionHandler {

    private val exceptionData = SingleLiveEvent<Throwable>()

    fun sendException(e: Throwable) {
        exceptionData.postValue(e)
    }

    fun getExceptionData(): LiveData<Throwable> = exceptionData
}