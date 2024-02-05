package com.example.data.datasource

import com.example.data.api.ApiService
import com.example.data.model.museum.Museum
import com.example.domain.commons.Result
import javax.inject.Inject

/**
 * MuseumDataSource for network calls
 */
class GetMuseumDataSource @Inject constructor(private val apiService: ApiService) {
    internal suspend fun getArtObjects(): Result<Museum> = apiService.getArtObjects()
}