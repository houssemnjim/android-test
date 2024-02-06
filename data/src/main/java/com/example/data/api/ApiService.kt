package com.example.data.api

import com.example.data.model.art_details.ArtObjectDetails
import com.example.data.model.museum.Museum
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.domain.commons.Result

interface ApiService {
    @GET("api/en/collection")
    suspend fun getArtObjects(): Result<Museum>

    @GET("api/en/collection/{object-number}")
    suspend fun getArtDetails(@Path(value = "object-number") objectNumber: String): Result<ArtObjectDetails>
}
