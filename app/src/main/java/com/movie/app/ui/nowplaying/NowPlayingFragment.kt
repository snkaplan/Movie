package com.movie.app.ui.nowplaying

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.movie.app.R
import com.movie.app.common.MOVIE_ID
import com.movie.app.common.snackbar
import com.movie.app.common.subscribe
import com.movie.app.databinding.NowPlayingFragmentBinding
import com.movie.app.ui.base.*
import com.movie.app.ui.upcoming.UpcomingRecyclerViewAdapter
import com.movie.domain.model.Movie
import com.movie.domain.model.MovieResult
import org.kodein.di.generic.instance

class NowPlayingFragment : BaseFragment() {
    private val viewModelFactory: NowPlayingViewModelFactory by instance()
    private lateinit var binding: NowPlayingFragmentBinding
    private lateinit var viewModel: NowPlayingViewModel
    private lateinit var rvAdapter: NowPlayingRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = NowPlayingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun viewReady() {
        handleUI()
        viewModel =
            ViewModelProvider(this, viewModelFactory)[NowPlayingViewModel::class.java]
        viewModel.getNowPlayingMovies()
        subscribeToData()
    }

    private fun handleUI() {
        rvAdapter =
            NowPlayingRecyclerViewAdapter(arrayListOf()) { selectedMovie: Movie, pos: Int ->
                movieItemClicked(selectedMovie,
                    pos)
            }
        binding.nowPlayingMovieRv.apply {
            setItemViewCacheSize(30)
            adapter = rvAdapter
        }

        binding.nowPlayingMovieRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollHorizontally(1)) {
                    viewModel.fetchMoreNowPlayingMovies()
                }
            }
        })
    }

    override fun getLayout() = R.layout.now_playing_fragment

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    private fun handleViewState(viewState: ViewState<MovieResult>) {
        when (viewState) {
            is Loading -> {
                binding.nowPlayingLoadingProgressBar.visibility = View.VISIBLE
            }
            is Success -> {
                val itemCount = rvAdapter.itemCount
                binding.nowPlayingLoadingProgressBar.visibility = View.GONE
                rvAdapter.reloadList(viewState.data.movies)
                rvAdapter.notifyItemRangeChanged(itemCount, rvAdapter.itemCount)
            }
            is Error -> {
                binding.nowPlayingLoadingProgressBar.visibility = View.GONE
                snackbar(viewState.error.localizedMessage ?: "Error happened while fetching movies",
                    requireView())
            }
            is NoInternetState -> {
                binding.nowPlayingLoadingProgressBar.visibility = View.GONE
                snackbar("There is no internet connection. Please make sure to you are connected to internet",
                    requireView())
            }
        }
    }

    private fun movieItemClicked(selectedMovie: Movie, pos: Int) {
        val bundle = bundleOf(MOVIE_ID to selectedMovie.id)
        findNavController().navigate(R.id.action_main_to_detail, bundle)
    }
}