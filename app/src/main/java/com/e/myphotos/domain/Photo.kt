package com.e.myphotos.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(

    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String
):Serializable
