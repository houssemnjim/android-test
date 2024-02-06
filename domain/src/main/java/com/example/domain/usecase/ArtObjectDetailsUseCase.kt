package com.example.domain.usecase

import com.example.domain.commons.Result
import com.example.domain.entities.ArtObjectDetailsEntity
import com.example.domain.repositories.ArtObjectDetailsRepository
import javax.inject.Inject

class ArtObjectDetailsUseCase @Inject constructor(private val artObjectDetailsRepository: ArtObjectDetailsRepository) {
    suspend fun getArtObjects(objectNumber: String): Result<ArtObjectDetailsEntity> =
        artObjectDetailsRepository.getArtDetails(objectNumber)
}