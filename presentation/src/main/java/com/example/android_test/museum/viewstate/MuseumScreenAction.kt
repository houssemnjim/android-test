package com.example.android_test.museum.viewstate

sealed class MuseumScreenAction {
    // View state for item click event
    data class DisplayArtObjectDetailsAction(val objectNumber: String) : MuseumScreenAction()
}