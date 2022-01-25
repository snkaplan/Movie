package com.movie.domain.usecase

import com.movie.domain.model.MovieResult
import com.movie.domain.model.Result

interface MovieUseCase {
    suspend fun getNowPlayingMoviesUseCase(page: Int): Result<MovieResult>
    suspend fun getUpcomingMoviesUseCase(page: Int): Result<MovieResult>
}