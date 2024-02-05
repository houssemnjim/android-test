package com.example.data.model.art_details


import com.google.gson.annotations.SerializedName

data class ArtObjectDetails(
    @SerializedName("artObject")
    val artObject: ArtObject?,
    @SerializedName("artObjectPage")
    val artObjectPage: ArtObjectPage?,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int?
)