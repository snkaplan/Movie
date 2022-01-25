package com.movie.domain.model

import java.util.*

data class Movie(
    val title: String,
    val releaseDate: Date,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
)