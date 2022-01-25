package com.movie.app.ui.upcoming

import com.movie.app.ui.base.BaseViewModel
import com.movie.app.ui.base.Error
import com.movie.app.ui.base.Success
import com.movie.domain.model.MovieResult
import com.movie.domain.model.onFailure
import com.movie.domain.model.onSuccess
import com.movie.domain.usecase.MovieUseCase

class UpcomingViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel<MovieResult>() {
    private var currentPage = 1

    fun getUpcomingMovies() = executeUseCase {
        movieUseCase.getUpcomingMoviesUseCase(currentPage)
            .onSuccess {
                _viewState.value = Success(it)
                currentPage++
            }
            .onFailure { _viewState.value = Error(it.throwable) }
    }
}