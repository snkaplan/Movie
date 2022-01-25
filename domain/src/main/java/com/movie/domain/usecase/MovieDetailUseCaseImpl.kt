package com.movie.domain.usecase

import com.movie.domain.model.Movie
import com.movie.domain.model.Result
import com.movie.domain.repository.MovieDetailRepository

class MovieDetailUseCaseImpl(private val movieDetailRepository: MovieDetailRepository) : MovieDetailUseCase {
    override suspend fun getMovieDetail(movieId: Int): Result<Movie> =
        movieDetailRepository.getMovieDetail(movieId)
}