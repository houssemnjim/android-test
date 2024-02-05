package com.example.domain.entities

data class ArtObjectDetailsEntity(
    val id: String?,
    val artTitle: String?,
    val artImage: String?,
    val description: String?,
    val makers: List<MakerEntity>?
)
