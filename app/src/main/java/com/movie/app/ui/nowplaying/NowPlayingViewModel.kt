package com.movie.app.ui.nowplaying

import com.movie.app.ui.base.BaseViewModel
import com.movie.app.ui.base.Success
import com.movie.domain.interaction.nowplaying.NowPlayingUseCase
import com.movie.domain.model.NowPlayingMovieInfo
import com.movie.domain.model.onFailure
import com.movie.domain.model.onSuccess
import com.movie.app.ui.base.Error

class NowPlayingViewModel(private val nowPlayingUseCase: NowPlayingUseCase) :
    BaseViewModel<NowPlayingMovieInfo>() {

    fun getNowPlayingMovies() = executeUseCase {
        nowPlayingUseCase("")
            .onSuccess { _viewState.value = Success(it) }
            .onFailure { _viewState.value = Error(it.throwable) }
    }
}