package com.example.urbandicionary.model

import com.google.gson.annotations.SerializedName


data class Definition(@SerializedName("definition")var definition: String?,
                      @SerializedName("permalink")var permalink: String?,
                      @SerializedName("thumbs_up")var thumbsUp: Int?,
                      @SerializedName("author")var author: String?,
                      @SerializedName("word")var word: String?,
                      @SerializedName("defid")var defid: Int?,
                      @SerializedName("current_vote")var currentVote: String?,
                      @SerializedName("written_on")var writtenOn: String?,
                      @SerializedName("example")var example: String?,
                      @SerializedName("thumbs_down")var thumbsDown: Int?)