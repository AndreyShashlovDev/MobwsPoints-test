package com.sprinter.mobws.mvvm.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sprinter.mobws.utils.ExceptionHandler

class MainViewModelFactory(private val exceptionHandler: ExceptionHandler) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(exceptionHandler) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
