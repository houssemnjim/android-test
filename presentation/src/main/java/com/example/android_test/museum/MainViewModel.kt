package com.example.android_test.museum

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_test.museum.viewstate.MuseumScreenAction
import com.example.android_test.view_element.factory.MuseumViewElementFactory
import com.example.android_test.view_element.model.MuseumElement
import com.example.domain.commons.Failure
import com.example.domain.commons.Success
import com.example.domain.usecase.ArtObjectDetailsUseCase
import com.example.domain.usecase.MuseumUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val museumUseCase: MuseumUseCase,
    private val museumViewElementFactory: MuseumViewElementFactory
) : ViewModel() {

    private val _museumViewElements = MutableStateFlow(MuseumElement(emptyList()))
    val museumViewElements = _museumViewElements.asStateFlow()

    private var _artObjectClicked : MutableLiveData<String> = MutableLiveData()
    var artObjectClicked: LiveData<String> = _artObjectClicked

    fun getMuseumArtObjects() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val museumResult = museumUseCase.getArtObjects()) {
                is Success -> {
                    _museumViewElements.tryEmit(
                        museumViewElementFactory.generateMuseumViewElement(museumResult.value)
                    )
                }

                is Failure -> {
                    Log.e("MUSEUM", "ERROR LOADING ${museumResult.message}")
                }
            }
        }
    }


    fun sendAction(museumAction: MuseumScreenAction) {
        when (museumAction) {
            is MuseumScreenAction.DisplayArtObjectDetailsAction -> {
                _artObjectClicked.value = museumAction.objectNumber
            }
        }

    }
}