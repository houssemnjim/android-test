package com.example.domain.repositories

import com.example.domain.commons.Result
import com.example.domain.entities.MuseumEntity

interface MuseumRepository {
    suspend fun getArtObjects(): Result<MuseumEntity>
}