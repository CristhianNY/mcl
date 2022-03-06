package com.mercadolibre.cristhianbonilla.mercadolibre.di.module

import com.mercadolibre.cristhianbonilla.mercadolibre.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
}