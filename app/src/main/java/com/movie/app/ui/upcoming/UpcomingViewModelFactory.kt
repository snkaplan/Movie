package com.movie.app.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movie.domain.usecase.MovieUseCase

class UpcomingViewModelFactory(
    private val nowPlayingUseCase: MovieUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UpcomingViewModel(nowPlayingUseCase) as T
    }
}