package com.mercadolibre.cristhianbonilla.mercadolibre.di

import android.app.Application
import android.content.Context
import com.mercadolibre.cristhianbonilla.mercadolibre.application.App
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.ActivityModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.ApiModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.AppModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.DataSourceModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.FragmentModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.NetworkModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.ProviderModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.RepositoryModule
import com.mercadolibre.cristhianbonilla.mercadolibre.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ProviderModule::class,
        DataSourceModule::class,
        FragmentModule::class,
        ActivityModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun getContext(): Context
}
