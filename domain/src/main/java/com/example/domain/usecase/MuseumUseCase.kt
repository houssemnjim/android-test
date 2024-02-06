package com.example.domain.usecase

import com.example.domain.commons.Result
import com.example.domain.entities.MuseumEntity
import com.example.domain.repositories.MuseumRepository
import javax.inject.Inject

class MuseumUseCase @Inject constructor(private val museumRepository: MuseumRepository) {
    suspend fun getArtObjects(): Result<MuseumEntity> = museumRepository.getArtObjects()
}