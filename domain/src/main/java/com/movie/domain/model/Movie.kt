package com.movie.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val imdbId: String?
)