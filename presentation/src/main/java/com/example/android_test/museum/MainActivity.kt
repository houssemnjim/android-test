package com.example.android_test.museum

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.android_test.art_details.ArtDetailsActivity
import com.example.android_test.museum.ui.MuseumScreen
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseumScreen()
        }

        this.viewModel = ViewModelProvider(this, this.viewModelFactory)[MainViewModel::class.java]

        viewModel.getMuseumArtObjects()

        // Observe click on items
        // i used live data here because it's lifecycle aware
        viewModel.artObjectClicked.observe(this) {
            if (it != "") {
                val intent = Intent(this, ArtDetailsActivity::class.java)
                intent.putExtra(OBJECT_NUMBER_EXTRA, it)
                startActivity(intent)
            }
        }
    }

    companion object {
        private const val OBJECT_NUMBER_EXTRA = "objectNumber"
    }
}
