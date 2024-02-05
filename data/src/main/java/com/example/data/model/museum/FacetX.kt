package com.example.data.model.museum


import com.google.gson.annotations.SerializedName

data class FacetX(
    @SerializedName("key")
    val key: String?,
    @SerializedName("value")
    val value: Int?
)