package com.sprinter.mobws.mvvm.main.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.sprinter.mobws.R
import com.sprinter.mobws.mvvm.main.vm.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }
}
