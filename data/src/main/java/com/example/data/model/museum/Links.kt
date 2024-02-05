package com.example.data.model.museum


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self")
    val self: String?,
    @SerializedName("web")
    val web: String?
)