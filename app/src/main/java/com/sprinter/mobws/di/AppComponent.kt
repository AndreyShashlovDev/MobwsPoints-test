package com.sprinter.mobws.di

import com.sprinter.mobws.AppDelegate
import com.sprinter.mobws.di.gson.GsonConfigModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ExceptionHandlerModule::class,
        GsonConfigModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        RouterModule::class,
        NetworkServiceModule::class,
        NetworkApiModule::class,
        RepositoriesModule::class,
        BuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<AppDelegate> {
    override fun inject(application: AppDelegate)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: DaggerApplication): Builder

        fun build(): AppComponent
    }
}
