package com.example.android_test.view_element.factory

import com.example.android_test.view_element.model.ArtObjectDetailsElement
import com.example.android_test.view_element.model.MakerElement
import com.example.domain.entities.ArtObjectDetailsEntity
import com.example.domain.entities.MakerEntity
import javax.inject.Inject

class ArtObjectDetailsViewElementFactory @Inject constructor() {

    fun generateArtObjectDetailsViewElement(artObjectDetailsEntity: ArtObjectDetailsEntity): ArtObjectDetailsElement =
        ArtObjectDetailsElement(
            artObjectDetailsEntity.id,
            artObjectDetailsEntity.artTitle,
            artObjectDetailsEntity.artImage,
            artObjectDetailsEntity.description,
            generateMakers(artObjectDetailsEntity.makers)
        )

    private fun generateMakers(makers: List<MakerEntity>?): List<MakerElement>? =
        makers?.map { MakerElement(it.name, it.placeOfBirth, it.birthDate, it.deathDate) }

}