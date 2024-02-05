package com.example.data.repositories

import com.example.data.datasource.GetMuseumDataSource
import com.example.data.mappers.MuseumMapper
import com.example.domain.commons.Failure
import com.example.domain.commons.Result
import com.example.domain.commons.Success
import com.example.domain.entities.MuseumEntity
import com.example.domain.repositories.MuseumRepository
import javax.inject.Inject

/**
 * An implementation of our MuseumRepo
 */
class MuseumRepositoryImpl @Inject constructor(
    private val museumDataSource: GetMuseumDataSource,
    private val museumMapper: MuseumMapper
) : MuseumRepository {

    override suspend fun getArtObjects(): Result<MuseumEntity> {
        return when (val resultMuseum = museumDataSource.getArtObjects()) {
            is Success -> Success(museumMapper.mapMuseum(resultMuseum.value))
            is Failure -> Failure(resultMuseum.message)
        }
    }

}