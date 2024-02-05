package com.example.data.mappers

import com.example.data.model.art_details.ArtObjectDetails
import com.example.data.model.art_details.PrincipalMaker
import com.example.domain.entities.ArtObjectDetailsEntity
import com.example.domain.entities.MakerEntity
import javax.inject.Inject

/**
 * ArtObjectDetailsMapper an API Model to Entity mapper
 */
class ArtObjectDetailsMapper @Inject constructor() {
    fun mapArtDetails(artObjectDetails: ArtObjectDetails): ArtObjectDetailsEntity =
        artObjectDetails.artObject.let {
            ArtObjectDetailsEntity(
                it?.objectNumber,
                it?.title,
                it?.webImage?.url,
                it?.description,
                mapMakers(it?.principalMakers)
            )
        }


    private fun mapMakers(makers: List<PrincipalMaker>?): List<MakerEntity>? =
        makers?.map {
            MakerEntity(it.name, it.placeOfBirth, it.dateOfBirth, it.dateOfDeath)
        }

}