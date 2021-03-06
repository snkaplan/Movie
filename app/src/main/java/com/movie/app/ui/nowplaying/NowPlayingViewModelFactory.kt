package com.movie.app.ui.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movie.domain.usecase.MovieUseCase

class NowPlayingViewModelFactory (
    private val nowPlayingUseCase: MovieUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  NowPlayingViewModel(nowPlayingUseCase) as T
    }
}