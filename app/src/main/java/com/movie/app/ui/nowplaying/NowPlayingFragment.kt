package com.movie.app.ui.nowplaying

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.movie.app.R
import com.movie.app.common.subscribe
import com.movie.app.databinding.NowPlayingFragmentBinding
import com.movie.app.ui.base.*
import com.movie.domain.model.NowPlayingMovieInfo
import org.kodein.di.generic.instance

class NowPlayingFragment : BaseFragment() {
    private val viewModelFactory: NowPlayingViewModelFactory by instance()
    private lateinit var binding: NowPlayingFragmentBinding
    private lateinit var viewModel: NowPlayingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = NowPlayingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun viewReady() {
        Log.d("TAG", "viewReady: View is ready")
        viewModel =
            ViewModelProvider(this, viewModelFactory)[NowPlayingViewModel::class.java]
        viewModel.getNowPlayingMovies()
        subscribeToData()
    }

    override fun getLayout() = R.layout.now_playing_fragment

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    private fun handleViewState(viewState: ViewState<NowPlayingMovieInfo>) {
        when (viewState) {
            is Loading -> Log.d("TAG", "Loading")
            is Success -> Log.d("TAG", "Success " + viewState.data)
            is Error -> Log.d("TAG", "handleViewState error: " + viewState.error.localizedMessage)
            is NoInternetState -> Log.d("TAG", "handleViewState: " + "showNoInternetError()")
        }
    }
}