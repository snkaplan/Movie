package com.movie.app.ui.nowplaying

import com.movie.app.ui.base.BaseViewModel
import com.movie.app.ui.base.Success
import com.movie.domain.usecase.MovieUseCase
import com.movie.domain.model.MovieResult
import com.movie.domain.model.onFailure
import com.movie.domain.model.onSuccess
import com.movie.app.ui.base.Error

class NowPlayingViewModel(private val movieUseCase: MovieUseCase) :
    BaseViewModel<MovieResult>() {
    private var currentPage = 1
    fun getNowPlayingMovies() = executeUseCase {
        movieUseCase.getNowPlayingMoviesUseCase(currentPage)
            .onSuccess {
                _viewState.value = Success(it)
            }
            .onFailure { _viewState.value = Error(it.throwable) }
    }

    fun fetchMoreNowPlayingMovies() = executeUseCase {
        currentPage++
        movieUseCase.getNowPlayingMoviesUseCase(currentPage)
            .onSuccess {
                _viewState.value = Success(it)
            }
            .onFailure { _viewState.value = Error(it.throwable) }
    }
}