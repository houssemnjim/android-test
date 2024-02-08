package com.example.android_test.museum.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.android_test.museum.MainViewModel
import com.example.android_test.view_element.model.ArtObjectElement

@Composable
fun MuseumScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    // we get the list of art from our model as a StateFlow and we collect that
    val artObjectsList by mainViewModel.museumViewElements.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(artObjectsList.artObjects) { item ->
            ArtItem(item = item, navController = navController, modifier)
        }
    }
}


// Our art item : a Card with the Image and the art title
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArtItem(item: ArtObjectElement, navController: NavController, modifier: Modifier) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("details/${item.id}")
            }) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = item.artImage,
                contentDescription = "Art Image",
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(width = 200.dp, height = 200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = item.artTitle ?: "",
                modifier = modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        }
    }
}