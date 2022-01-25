package com.movie.domain.repository

import com.movie.domain.model.MovieResult
import com.movie.domain.model.Result

interface MovieRepository {
    suspend fun getNowPlayingMovies(page: Int): Result<MovieResult>
    suspend fun getUpcomingMovies(page: Int): Result<MovieResult>
}