package com.example.urbandicionary.di.module

import com.example.urbandicionary.ui.fragment.DefinitionDetailFragment
import com.example.urbandicionary.ui.fragment.DefinitionListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun provideDefinitionListFragment(): DefinitionListFragment

    @ContributesAndroidInjector
    internal abstract fun provideDefinitionDetailFragment(): DefinitionDetailFragment


}