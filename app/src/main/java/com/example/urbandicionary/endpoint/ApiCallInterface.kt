package com.example.urbandicionary.endpoint

import com.example.urbandicionary.model.ListResult
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.GET


interface ApiCallInterface {


    @Headers(
        "X-RapidAPI-Host:mashape-community-urban-dictionary.p.rapidapi.com",
        "X-RapidAPI-Key:6e3bd4891fmsh23e832e69615480p11c531jsn140fd996db97"
    )
    @GET("/define?")
    //fun getDefinitions(@Query("term") term: String): Observable<Listresult<Definition>>
    fun getDefinitions(@Query("term") term: String?): Observable<ListResult>

}
