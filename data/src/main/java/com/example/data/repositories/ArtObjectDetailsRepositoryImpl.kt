package com.example.data.repositories

import com.example.data.datasource.GetArtDetailsDataSource
import com.example.data.mappers.ArtObjectDetailsMapper
import com.example.domain.commons.Failure
import com.example.domain.commons.Result
import com.example.domain.commons.Success
import com.example.domain.entities.ArtObjectDetailsEntity
import com.example.domain.repositories.ArtObjectDetailsRepository
import javax.inject.Inject

/**
 * An implementation of our ArtObjectDetailsRepo
 */
class ArtObjectDetailsRepositoryImpl @Inject constructor(
    private val artObjectDetailsDataSource: GetArtDetailsDataSource,
    private val artObjectDetailsMapper: ArtObjectDetailsMapper
) : ArtObjectDetailsRepository {

    override suspend fun getArtDetails(objectNumber: String): Result<ArtObjectDetailsEntity> {
        return when (val resultArtDetails =
            artObjectDetailsDataSource.getArtDetails(objectNumber)) {
            is Success -> Success(artObjectDetailsMapper.mapArtDetails(resultArtDetails.value))
            is Failure -> Failure(resultArtDetails.message)
        }
    }

}