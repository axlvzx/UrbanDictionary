package com.example.urbandicionary.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.urbandicionary.viewmodel.DefinitionViewModel
import com.example.urbandicionary.factory.ViewModelFactory
import com.example.urbandicionary.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey( DefinitionViewModel::class )
    abstract fun mainViewModel( mainViewModel: DefinitionViewModel ): ViewModel

    @Binds
    abstract fun viewModelFactory( factory: ViewModelFactory):
            ViewModelProvider.Factory

}