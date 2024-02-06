package com.example.data.model.museum


import com.google.gson.annotations.SerializedName

data class Museum(
    @SerializedName("artObjects")
    val artObjects: List<ArtObject>,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("countFacets")
    val countFacets: CountFacets?,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int?,
    @SerializedName("facets")
    val facets: List<Facet>?
)