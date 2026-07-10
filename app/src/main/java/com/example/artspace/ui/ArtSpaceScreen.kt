package com.example.artspace.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.artspace.R
import com.example.artspace.data.ArtworkRepository
import com.example.artspace.model.Artwork

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val artworks = ArtworkRepository.artworks
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }

    val isFirst = currentIndex == 0
    val isLast = currentIndex == artworks.lastIndex
    val currentArtwork = artworks[currentIndex]

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                textAlign = TextAlign.Center,
            )
        },
    ) { innerPadding ->
        if (isLandscape) {
            LandscapeLayout(
                artwork = currentArtwork,
                isFirst = isFirst,
                isLast = isLast,
                onPrevious = { if (!isFirst) currentIndex -= 1 },
                onNext = { if (!isLast) currentIndex += 1 },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
            )
        } else {
            PortraitLayout(
                artwork = currentArtwork,
                isFirst = isFirst,
                isLast = isLast,
                onPrevious = { if (!isFirst) currentIndex -= 1 },
                onNext = { if (!isLast) currentIndex += 1 },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
            )
        }
    }
}

@Composable
private fun PortraitLayout(
    artwork: Artwork,
    isFirst: Boolean,
    isLast: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        ArtworkImage(
            artwork = artwork,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        ArtworkInfo(artwork = artwork)
        Spacer(modifier = Modifier.height(24.dp))
        NavigationButtons(
            isFirst = isFirst,
            isLast = isLast,
            onPrevious = onPrevious,
            onNext = onNext,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun LandscapeLayout(
    artwork: Artwork,
    isFirst: Boolean,
    isLast: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ArtworkImage(
            artwork = artwork,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(end = 16.dp),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            ArtworkInfo(artwork = artwork)
            NavigationButtons(
                isFirst = isFirst,
                isLast = isLast,
                onPrevious = onPrevious,
                onNext = onNext,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun ArtworkImage(
    artwork: Artwork,
    modifier: Modifier = Modifier,
) {
    val description = stringResource(artwork.imageDescriptionResId)
    Image(
        painter = painterResource(artwork.imageResId),
        contentDescription = description,
        contentScale = ContentScale.Fit,
        modifier = modifier.semantics { contentDescription = description },
    )
}

@Composable
private fun ArtworkInfo(
    artwork: Artwork,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(artwork.titleResId),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.artwork_author_label, stringResource(artwork.authorResId)),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.artwork_year_label, stringResource(artwork.yearResId)),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun NavigationButtons(
    isFirst: Boolean,
    isLast: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val previousHint = stringResource(R.string.button_previous_hint)
    val nextHint = stringResource(R.string.button_next_hint)

    Row(
        modifier = modifier.widthIn(max = 480.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = onPrevious,
            enabled = !isFirst,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .semantics { contentDescription = previousHint },
        ) {
            Text(text = stringResource(R.string.button_previous))
        }
        Button(
            onClick = onNext,
            enabled = !isLast,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .semantics { contentDescription = nextHint },
        ) {
            Text(text = stringResource(R.string.button_next))
        }
    }
}
