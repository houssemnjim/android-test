package com.example.domain.repositories

import com.example.domain.commons.Result
import com.example.domain.entities.ArtObjectDetailsEntity

interface ArtObjectDetailsRepository {
    suspend fun getArtDetails(objectNumber: String): Result<ArtObjectDetailsEntity>
}