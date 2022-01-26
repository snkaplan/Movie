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
import android.content.Intent
import android.net.Uri
import com.movie.app.common.IMDB_REDIRECT_BASE_URL
import com.movie.app.common.TMDB_IMAGE_BASE_URL


class MovieDetailFragment : BaseFragment() {
    private val viewModelFactory: MovieDetailViewModelFactory by instance()
    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewModel: MovieDetailViewModel
    private var currentMovie: Movie? = null
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
        binding.imdbLogoIv.setOnClickListener{
            currentMovie?.let {
                it.imdbId?.let { id ->
                    val viewIntent = Intent("android.intent.action.VIEW",
                        Uri.parse("${IMDB_REDIRECT_BASE_URL}${id}"))
                    startActivity(viewIntent)
                }

            }

        }
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
                currentMovie = viewState.data
                currentMovie?.let {
                    binding.groupLoading.visibility = View.VISIBLE
                    binding.detailLoadingProgressBar.visibility = View.GONE
                    binding.titleTv.text = it.title
                    binding.overviewTv.text = it.overview
                    binding.releaseDateTv.text = it.releaseDate
                    binding.voteTv.text = it.voteAverage.toString()
                    viewState.data.posterPath?.let { posterPath ->
                        Glide.with(requireView())
                            .load("${TMDB_IMAGE_BASE_URL}${posterPath}")
                            .into(binding.movieDetailIv)
                    }
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