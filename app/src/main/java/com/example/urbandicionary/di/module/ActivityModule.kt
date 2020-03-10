package com.example.urbandicionary.di.module

import com.example.urbandicionary.adapter.DefinitionAdapter
import com.example.urbandicionary.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class, AdapterModule::class])
    abstract fun contributeMainActivity(): MainActivity
    abstract fun contributeDefinitionAdapter(): DefinitionAdapter


}