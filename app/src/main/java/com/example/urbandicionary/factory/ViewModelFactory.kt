package com.example.urbandicionary.factory

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.urbandicionary.repository.Repository
import com.example.urbandicionary.viewmodel.DefinitionViewModel


import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory
@Inject
constructor(private val repository: Repository) : ViewModelProvider.Factory {

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DefinitionViewModel::class.java)) {
            val key = "DefinitionViewModel"
            if(hashMapViewModel.containsKey(key)){
                return getViewModel(
                    key
                ) as T
            } else {
                addViewModel(
                    key,
                    DefinitionViewModel(repository)
                )
                return getViewModel(
                    key
                ) as T
            }
        }
        throw IllegalArgumentException("Unknown class name")
    }

    companion object {
        val hashMapViewModel = HashMap<String, ViewModel>()
        fun addViewModel(key: String, viewModel: ViewModel){
            hashMapViewModel.put(key, viewModel)
        }
        fun getViewModel(key: String): ViewModel? {
            return hashMapViewModel[key]
        }
    }
}