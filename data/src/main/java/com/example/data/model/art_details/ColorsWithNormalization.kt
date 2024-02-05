package com.example.data.model.art_details


import com.google.gson.annotations.SerializedName

data class ColorsWithNormalization(
    @SerializedName("normalizedHex")
    val normalizedHex: String?,
    @SerializedName("originalHex")
    val originalHex: String?
)