package com.example.data.model.museum


import com.google.gson.annotations.SerializedName

data class CountFacets(
    @SerializedName("hasimage")
    val hasimage: Int?,
    @SerializedName("ondisplay")
    val ondisplay: Int?
)