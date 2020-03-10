package com.example.urbandicionary.di.component


import com.example.urbandicionary.MyApplication
import com.example.urbandicionary.di.module.ActivityModule
import com.example.urbandicionary.di.module.AppModule
import com.example.urbandicionary.di.module.MainModule

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton



@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityModule::class, MainModule::class])
interface AppComponent{
    fun inject(myApplication: MyApplication)

}