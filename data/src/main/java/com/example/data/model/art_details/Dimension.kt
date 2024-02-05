package com.example.data.model.art_details


import com.google.gson.annotations.SerializedName

data class Dimension(
    @SerializedName("part")
    val part: String?,
    @SerializedName("precision")
    val precision: Any?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("unit")
    val unit: String?,
    @SerializedName("value")
    val value: String?
)