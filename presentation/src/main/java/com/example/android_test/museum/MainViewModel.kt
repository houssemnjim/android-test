package com.example.android_test.museum

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_test.view_element.factory.MuseumViewElementFactory
import com.example.android_test.view_element.model.MuseumElement
import com.example.domain.commons.Failure
import com.example.domain.commons.Success
import com.example.domain.usecase.MuseumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val museumUseCase: MuseumUseCase,
    private val museumViewElementFactory: MuseumViewElementFactory
) : ViewModel() {

    init {
        fetchMuseumArtObjects()
    }

    private val _museumViewElements = MutableStateFlow(MuseumElement(emptyList()))
    val museumViewElements = _museumViewElements.asStateFlow()

    private fun fetchMuseumArtObjects() {
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

}