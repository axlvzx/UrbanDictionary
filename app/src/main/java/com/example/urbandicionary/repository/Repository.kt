package com.example.urbandicionary.repository

import com.example.urbandicionary.endpoint.ApiCallInterface
import com.example.urbandicionary.model.ListResult


import io.reactivex.Observable


class Repository(private val apiCallInterface: ApiCallInterface) {

    fun getDefinitions(term: String?): Observable<ListResult> {
        return apiCallInterface.getDefinitions(term)
    }

}
