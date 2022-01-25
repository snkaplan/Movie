package com.movie.domain.usecase

import com.movie.domain.model.Movie
import com.movie.domain.model.Result

interface MovieDetailUseCase {
    suspend fun getMovieDetail(movieId: Int): Result<Movie>
}