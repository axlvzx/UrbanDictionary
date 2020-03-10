package com.example.urbandicionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urbandicionary.repository.Repository
import com.example.urbandicionary.utils.ApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class DefinitionViewModel(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()

    private val responseLiveData = MutableLiveData<ApiResponse>()

    private var orderBy = MutableLiveData<Int>().apply {
        value = 1
    }


    fun getDefinitions(): MutableLiveData<ApiResponse> {
        return responseLiveData
    }

    fun callDefinitionsRx(term: String?) {

        disposables.add(repository.getDefinitions(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { d -> responseLiveData.setValue(ApiResponse.loading()) }
            .subscribe(
                { result -> responseLiveData.setValue(ApiResponse.success(result)) },
                { throwable -> responseLiveData.setValue(ApiResponse.error(throwable)) }
            ))

    }


    fun setOrderBy(_orderBy: Int){

        orderBy.value = _orderBy
    }

    fun getOrderBy(): MutableLiveData<Int>{
        return orderBy
    }

    override fun onCleared() {
        disposables.clear()
    }
}
