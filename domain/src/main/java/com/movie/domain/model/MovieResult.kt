package com.movie.domain.model

data class MovieResult(
    val currentPage: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<Movie>,
)