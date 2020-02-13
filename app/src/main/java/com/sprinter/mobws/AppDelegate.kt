package com.sprinter.mobws

import android.os.StrictMode
import com.sprinter.mobws.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import timber.log.Timber

class AppDelegate : DaggerApplication() {

    val dispatchingAndroidInjector =
        DaggerAppComponent
            .builder()
            .application(this)
            .build()

    override fun onCreate() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        dispatchingAndroidInjector
}