package com.sprinter.mobws.mvvm.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sprinter.mobws.utils.ExceptionHandler

class MainViewModel(exceptionHandler: ExceptionHandler) : ViewModel() {

    val errorMessage: LiveData<Throwable> = exceptionHandler.getExceptionData()
}
