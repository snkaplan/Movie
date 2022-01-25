package com.movie.domain.repository

import com.movie.domain.model.Movie
import com.movie.domain.model.Result

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): Result<Movie>
}