package com.example.android_test.museum.viewstate

sealed class MuseumScreenAction {
    data class DisplayArtObjectDetailsAction(val objectNumber: String) : MuseumScreenAction()
}