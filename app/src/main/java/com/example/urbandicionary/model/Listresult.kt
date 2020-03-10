package com.example.urbandicionary.model

import com.google.gson.annotations.SerializedName

data class ListResult(@SerializedName("list") var results:List<Definition>)