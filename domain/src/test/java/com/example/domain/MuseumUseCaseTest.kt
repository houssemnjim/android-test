package com.example.domain

import com.example.domain.commons.Failure
import com.example.domain.commons.Success
import com.example.domain.entities.MuseumEntity
import com.example.domain.repositories.MuseumRepository
import com.example.domain.usecase.MuseumUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class MuseumUseCaseTest : BaseTest() {

    @Mock
    private lateinit var museumRepository: MuseumRepository

    @InjectMocks
    private lateinit var museumUseCase: MuseumUseCase

    @Test
    fun `when invoke MuseumUseCase should return the good result`() {
        runTest {
            val response = DomainTestUtils.generateMuseumEntity()
            val successResponse = Success(response)
            Mockito.`when`(museumRepository.getArtObjects()).thenReturn(successResponse)
            val result = museumUseCase.getArtObjects()
            result as Success<MuseumEntity>
            val data: MuseumEntity = result.value

            Assertions.assertEquals(data, response)
        }
    }

    @Test
    fun `UseCase should return error result when repo return error`() {
        runTest {
            val errorResponse = Failure<MuseumEntity>("any error")
            Mockito.`when`(museumRepository.getArtObjects()).thenReturn(errorResponse)
            val result = museumUseCase.getArtObjects()
            result as Failure<MuseumEntity>

            Assertions.assertEquals(result.message, "any error")
        }
    }
}