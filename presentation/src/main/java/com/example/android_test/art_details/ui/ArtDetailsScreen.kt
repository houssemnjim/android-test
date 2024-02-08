package com.example.android_test.art_details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.android_test.R
import com.example.android_test.art_details.ArtDetailsViewModel
import com.example.android_test.view_element.model.MakerElement

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ArtDetailsScreen(
    navController: NavController,
    viewModel: ArtDetailsViewModel,
    itemId: String?,
    modifier: Modifier = Modifier
) {
    val artObjectItem by viewModel.detailsViewElements.collectAsStateWithLifecycle()

    // Creating a scroll state for the column
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.art_details_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = { paddingValue ->
            Column(
                modifier = modifier
                    .padding(paddingValue)
                    .background(Color.White)
                    .verticalScroll(scrollState)
            ) {
                GlideImage(
                    model = artObjectItem?.artImage,
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = artObjectItem?.artTitle ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = modifier
                        .padding(start = 12.dp, end = 12.dp)
                )
                Spacer(modifier = modifier.height(8.dp))
                artObjectItem?.description?.let {
                    Text(
                        text = it, modifier = modifier
                            .padding(start = 12.dp, end = 12.dp)
                    )
                }
                Spacer(modifier = modifier.height(16.dp))
                ArtistInfoView(artObjectItem?.makers?.get(0), modifier)
            }
        })

}

// Maker item
@Composable
fun ArtistInfoView(maker: MakerElement?, modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)
    ) {
        Text(text = "Artist: ${maker?.name}", fontWeight = FontWeight.Bold)
        Text(text = "Birthplace: ${maker?.placeOfBirth}")
        Text(text = "Born: ${maker?.birthDate}")
        Text(text = "Died: ${maker?.deathDate}")
    }
}

