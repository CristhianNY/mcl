package com.mercadolibre.cristhianbonilla.mercadolibre.di.module

import com.mercadolibre.cristhianbonilla.mercadolibre.product_list.ProductListFragment
import com.mercadolibre.cristhianbonilla.mercadolibre.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    internal abstract fun bindProductListFragment(): ProductListFragment
}