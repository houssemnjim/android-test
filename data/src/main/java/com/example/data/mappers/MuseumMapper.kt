package com.example.data.mappers

import com.example.data.model.museum.ArtObject
import com.example.data.model.museum.Museum
import com.example.domain.entities.ArtObjectEntity
import com.example.domain.entities.MuseumEntity
import javax.inject.Inject

/**
 * MuseumMapper an API Model to Entity mapper
 */
class MuseumMapper @Inject constructor() {
    fun mapMuseum(museum: Museum): MuseumEntity =
        MuseumEntity(
            museum.artObjects.map {
                mapArtObject(it)
            }
        )

    private fun mapArtObject(artObject: ArtObject): ArtObjectEntity {

        return ArtObjectEntity(artObject.objectNumber, artObject.title, artObject.webImage?.url)
    }


}