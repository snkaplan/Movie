package com.movie.domain.usecase

import com.movie.domain.model.MovieResult
import com.movie.domain.model.Result
import com.movie.domain.repository.MovieRepository

class MovieUseCaseImpl(private val movieRepository: MovieRepository) :
    MovieUseCase {
    override suspend fun getNowPlayingMoviesUseCase(page: Int): Result<MovieResult> =
        movieRepository.getNowPlayingMovies(page)

    override suspend fun getUpcomingMoviesUseCase(page: Int): Result<MovieResult> =
        movieRepository.getUpcomingMovies(page)

    override suspend fun refreshNowPlayingMoviesUseCase(page: Int): Result<MovieResult> =
        movieRepository.refreshNowPlayingMovies(page)

    override suspend fun refreshUpcomingMoviesUseCase(page: Int): Result<MovieResult> =
        movieRepository.refreshUpcomingMovies(page)
}