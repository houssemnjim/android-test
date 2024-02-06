package com.example.android_test.art_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_test.view_element.factory.ArtObjectDetailsViewElementFactory
import com.example.android_test.view_element.model.ArtObjectDetailsElement
import com.example.domain.commons.Failure
import com.example.domain.commons.Success
import com.example.domain.usecase.ArtObjectDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtDetailsViewModel @Inject constructor(
    private val artObjectDetailsUseCase: ArtObjectDetailsUseCase,
    private val detailsViewElementFactory: ArtObjectDetailsViewElementFactory
) : ViewModel() {

    private val _detailsViewElements = MutableStateFlow<ArtObjectDetailsElement?>(null)
    val detailsViewElements = _detailsViewElements.asStateFlow()

    fun getArtDetails(objectNumber: String) {
        viewModelScope.launch(Dispatchers.Default) {
            when (val detailsResult = artObjectDetailsUseCase.getArtObjects(objectNumber)) {
                is Success -> {
                    _detailsViewElements.tryEmit(
                        detailsViewElementFactory.generateArtObjectDetailsViewElement(detailsResult.value)
                    )
                }

                is Failure -> {
                    Log.e("DETAILS", "ERROR DETAILS : ${detailsResult.message}")
                }
            }
        }
    }

}