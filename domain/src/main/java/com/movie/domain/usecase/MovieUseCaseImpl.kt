package com.movie.domain.usecase

import com.movie.domain.model.MovieResult
import com.movie.domain.model.Result
import com.movie.domain.repository.MovieRepository

class MovieUseCaseImpl(private val nowPlayingMovieRepository: MovieRepository) :
    MovieUseCase {
    override suspend fun getNowPlayingMoviesUseCase(page: Int): Result<MovieResult> =
        nowPlayingMovieRepository.getNowPlayingMovies(page)

    override suspend fun getUpcomingMoviesUseCase(page: Int): Result<MovieResult> =
        nowPlayingMovieRepository.getUpcomingMovies(page)

}