package com.movie.domain.model

data class NowPlayingMovieInfo(
    val currentPage: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<MovieInfo>,
) {
}