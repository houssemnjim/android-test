package com.example.data.model.art_details


import com.google.gson.annotations.SerializedName

data class AdlibOverrides(
    @SerializedName("etiketText")
    val etiketText: Any?,
    @SerializedName("maker")
    val maker: Any?,
    @SerializedName("titel")
    val titel: Any?
)