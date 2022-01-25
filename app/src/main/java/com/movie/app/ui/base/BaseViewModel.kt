package com.movie.app.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movie.app.common.launch

abstract class BaseViewModel<T : Any> : ViewModel() {
    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState
    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        _viewState.value = Loading()
        launch { action() }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        _viewState.value = Loading()
        launch { action() }
    }
}