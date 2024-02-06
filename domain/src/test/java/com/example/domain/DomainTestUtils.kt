package com.example.domain

import com.example.domain.entities.ArtObjectEntity
import com.example.domain.entities.MuseumEntity

object DomainTestUtils {
    fun generateMuseumEntity() =
        MuseumEntity(
            listOf(
                ArtObjectEntity(
                    "AR-TEST",
                    "Art Title",
                    "ArtImageUrl"
                )
            )
        )
}