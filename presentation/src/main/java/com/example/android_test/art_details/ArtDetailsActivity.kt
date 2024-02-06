package com.example.android_test.art_details

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.android_test.art_details.ui.ArtDetailsScreen
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArtDetailsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ArtDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtDetailsScreen() {
                finish()
            }
        }

        this.viewModel =
            ViewModelProvider(this, this.viewModelFactory)[ArtDetailsViewModel::class.java]

        val objectNumber = intent.getStringExtra(OBJECT_NUMBER_EXTRA) ?: ""
        viewModel.getArtDetails(objectNumber)
    }

    companion object {
        private const val OBJECT_NUMBER_EXTRA = "objectNumber"
    }
}