package com.example.data.model.museum


import com.google.gson.annotations.SerializedName

data class Facet(
    @SerializedName("facets")
    val facets: List<FacetX>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("otherTerms")
    val otherTerms: Int?,
    @SerializedName("prettyName")
    val prettyName: Int?
)