package com.example.urbandicionary.di.module

import com.example.urbandicionary.adapter.DefinitionAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    internal fun provideDefinitionAdapter(): DefinitionAdapter {
        return DefinitionAdapter()
    }



}