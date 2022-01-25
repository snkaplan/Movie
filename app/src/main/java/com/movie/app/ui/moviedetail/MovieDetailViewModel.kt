package com.movie.app.ui.moviedetail

import com.movie.app.ui.base.BaseViewModel
import com.movie.app.ui.base.Error
import com.movie.app.ui.base.Success
import com.movie.domain.model.Movie
import com.movie.domain.model.onFailure
import com.movie.domain.model.onSuccess
import com.movie.domain.usecase.MovieDetailUseCase

class MovieDetailViewModel(
    private val movieDetailUseCAse: MovieDetailUseCase,
) : BaseViewModel<Movie>() {

    fun getMovieDetail(movieId: Int) = executeUseCase {
        movieDetailUseCAse.getMovieDetail(movieId)
            .onSuccess {
                _viewState.value = Success(it)
            }
            .onFailure { _viewState.value = Error(it.throwable) }
    }
}