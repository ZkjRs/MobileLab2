package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.Artwork

object ArtworkRepository {
    val artworks: List<Artwork> = listOf(
        Artwork(
            imageResId = R.drawable.art_monalisa,
            titleResId = R.string.art_monalisa_title,
            authorResId = R.string.art_monalisa_author,
            yearResId = R.string.art_monalisa_year,
            imageDescriptionResId = R.string.art_monalisa_description,
        ),
        Artwork(
            imageResId = R.drawable.art_starry_night,
            titleResId = R.string.art_starry_night_title,
            authorResId = R.string.art_starry_night_author,
            yearResId = R.string.art_starry_night_year,
            imageDescriptionResId = R.string.art_starry_night_description,
        ),
        Artwork(
            imageResId = R.drawable.art_the_scream,
            titleResId = R.string.art_the_scream_title,
            authorResId = R.string.art_the_scream_author,
            yearResId = R.string.art_the_scream_year,
            imageDescriptionResId = R.string.art_the_scream_description,
        ),
        Artwork(
            imageResId = R.drawable.art_pearl_earring,
            titleResId = R.string.art_pearl_earring_title,
            authorResId = R.string.art_pearl_earring_author,
            yearResId = R.string.art_pearl_earring_year,
            imageDescriptionResId = R.string.art_pearl_earring_description,
        ),
        Artwork(
            imageResId = R.drawable.art_sunflowers,
            titleResId = R.string.art_sunflowers_title,
            authorResId = R.string.art_sunflowers_author,
            yearResId = R.string.art_sunflowers_year,
            imageDescriptionResId = R.string.art_sunflowers_description,
        ),
    )
}
