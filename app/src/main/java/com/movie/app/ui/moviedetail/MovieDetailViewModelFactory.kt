package com.movie.app.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movie.domain.usecase.MovieDetailUseCase

class MovieDetailViewModelFactory(
    private val movieDetailUseCAse: MovieDetailUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(movieDetailUseCAse) as T
    }
}