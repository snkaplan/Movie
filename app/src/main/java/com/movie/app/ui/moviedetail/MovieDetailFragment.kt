package com.movie.app.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.movie.app.R
import com.movie.app.common.MOVIE_ID
import com.movie.app.common.subscribe
import com.movie.app.databinding.MovieDetailFragmentBinding
import com.movie.app.ui.base.*
import com.movie.domain.model.Movie
import org.kodein.di.generic.instance

class MovieDetailFragment : BaseFragment() {
    private val viewModelFactory: MovieDetailViewModelFactory by instance()
    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewModel: MovieDetailViewModel
    private var movieId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        movieId = arguments?.getInt(MOVIE_ID) ?: 1
        return binding.root
    }

    override fun viewReady() {
        viewModel =
            ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
        viewModel.getMovieDetail(movieId)
        subscribeToData()
    }

    override fun getLayout() = R.layout.movie_detail_fragment

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    private fun handleViewState(viewState: ViewState<Movie>) {
        when (viewState) {
            is Loading -> {
                binding.groupLoading.visibility = View.GONE
                binding.detailLoadingProgressBar.visibility = View.VISIBLE
            }
            is Success -> {
                binding.groupLoading.visibility = View.VISIBLE
                binding.detailLoadingProgressBar.visibility = View.GONE
                binding.titleTv.text = viewState.data.title
                binding.overviewTv.text = viewState.data.overview
                binding.releaseDateTv.text = viewState.data.releaseDate
                binding.voteTv.text = viewState.data.voteAverage.toString()
                viewState.data.posterPath?.let {
                    Glide.with(requireView())
                        .load("https://image.tmdb.org/t/p/w185/${it}")
                        .into(binding.movieDetailIv)
                }
            }
            is Error -> {
                binding.detailLoadingProgressBar.visibility = View.GONE
                Log.d("TAG", "handleViewState error: " + viewState.error.localizedMessage)
            }
            is NoInternetState -> {
                binding.detailLoadingProgressBar.visibility = View.GONE
                Log.d("TAG", "handleViewState: " + "showNoInternetError()")
            }
        }
    }
}