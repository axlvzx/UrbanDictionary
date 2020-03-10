package com.example.urbandicionary.di.component

import com.example.urbandicionary.di.module.ViewModelModule
import com.example.urbandicionary.viewmodel.DefinitionViewModel

import dagger.Component

@Component( modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun inject( mainViewModel: DefinitionViewModel)

}

