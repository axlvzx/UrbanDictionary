package com.example.urbandicionary.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val applicationContext: Context) {

    @Provides fun provideApplicationContext(): Context = applicationContext
}