package com.example.data.datasource

import com.example.data.api.ApiService
import com.example.data.model.art_details.ArtObjectDetails
import com.example.domain.commons.Result
import javax.inject.Inject

/**
 * ArtDetailsDataSource for network calls
 */
class GetArtDetailsDataSource @Inject constructor(private val apiService: ApiService) {
    internal suspend fun getArtDetails(objectNumber: String): Result<ArtObjectDetails> =
        apiService.getArtDetails(objectNumber)
}