package com.example.android_test.view_element.factory

import com.example.android_test.view_element.model.ArtObjectElement
import com.example.android_test.view_element.model.MuseumElement
import com.example.domain.entities.ArtObjectEntity
import com.example.domain.entities.MuseumEntity
import javax.inject.Inject

class MuseumViewElementFactory @Inject constructor() {

    fun generateMuseumViewElement(museumEntity: MuseumEntity): MuseumElement =
        MuseumElement(
            museumEntity.artObjects.map { generateArtObjectElement(it) }
        )

    private fun generateArtObjectElement(artObjectEntity: ArtObjectEntity): ArtObjectElement =
        ArtObjectElement(artObjectEntity.id, artObjectEntity.artTitle, artObjectEntity.artImage)

}