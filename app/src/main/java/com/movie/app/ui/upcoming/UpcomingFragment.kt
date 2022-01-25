package com.movie.app.ui.upcoming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.app.R
import com.movie.app.common.subscribe
import com.movie.app.databinding.NowPlayingFragmentBinding
import com.movie.app.databinding.UpcomingFragmentBinding
import com.movie.app.ui.base.*
import com.movie.app.ui.nowplaying.NowPlayingViewModel
import com.movie.app.ui.nowplaying.NowPlayingViewModelFactory
import com.movie.domain.model.Movie
import com.movie.domain.model.MovieResult
import org.kodein.di.generic.instance
import java.util.*

class UpcomingFragment : BaseFragment() {
    private val viewModelFactory: UpcomingViewModelFactory by instance()
    private lateinit var binding: UpcomingFragmentBinding
    private lateinit var viewModel: UpcomingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = UpcomingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun viewReady() {
        viewModel =
            ViewModelProvider(this, viewModelFactory)[UpcomingViewModel::class.java]
        viewModel.getUpcomingMovies()
        subscribeToData()
    }

    override fun getLayout() = R.layout.now_playing_fragment

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    private fun handleViewState(viewState: ViewState<MovieResult>) {
        when (viewState) {
            is Loading -> Log.d("TAG", "Loading")
            is Success -> {
                Log.d("TAG", "Success " + viewState.data)
                binding.upcomingMovieRv.apply {
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    setItemViewCacheSize(30)
                    adapter =
                        UpcomingRecyclerViewAdapter(viewState.data.movies) { selectedMovie: Movie, pos: Int ->
                            movieItemClicked(selectedMovie,
                                pos)
                        }
                }
            }
            is Error -> Log.d("TAG", "handleViewState error: " + viewState.error.localizedMessage)
            is NoInternetState -> Log.d("TAG", "handleViewState: " + "showNoInternetError()")
        }
    }

    private fun movieItemClicked(selectedMovie: Movie, pos: Int) {
        Log.d("TAG", "movieItemClicked: " + selectedMovie.title)
    }
}