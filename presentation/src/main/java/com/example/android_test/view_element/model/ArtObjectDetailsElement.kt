package com.example.android_test.view_element.model

data class ArtObjectDetailsElement(
    val id: String?,
    val artTitle: String?,
    val artImage: String?,
    val description: String?,
    val makers: List<MakerElement>?
)
