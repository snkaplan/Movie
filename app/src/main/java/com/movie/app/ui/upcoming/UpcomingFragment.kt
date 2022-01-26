package com.movie.app.ui.upcoming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.movie.app.R
import com.movie.app.common.MOVIE_ID
import com.movie.app.common.subscribe
import com.movie.app.databinding.UpcomingFragmentBinding
import com.movie.app.ui.base.*
import com.movie.domain.model.Movie
import com.movie.domain.model.MovieResult
import org.kodein.di.generic.instance
import androidx.recyclerview.widget.DividerItemDecoration
import com.movie.app.common.snackbar

import androidx.core.content.ContextCompat


class UpcomingFragment : BaseFragment() {
    private val viewModelFactory: UpcomingViewModelFactory by instance()
    private lateinit var binding: UpcomingFragmentBinding
    private lateinit var viewModel: UpcomingViewModel
    private lateinit var rvAdapter: UpcomingRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = UpcomingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun viewReady() {
        handleUI()
        viewModel =
            ViewModelProvider(this, viewModelFactory)[UpcomingViewModel::class.java]
        viewModel.getUpcomingMovies()
        subscribeToData()
    }

    override fun getLayout() = R.layout.now_playing_fragment

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
    }


    private fun handleUI() {
        rvAdapter =
            UpcomingRecyclerViewAdapter(arrayListOf<Movie>()) { selectedMovie: Movie, pos: Int ->
                movieItemClicked(selectedMovie,
                    pos)
            }
        binding.upcomingMovieRv.apply {
            setItemViewCacheSize(30)
            adapter = rvAdapter
            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_line)!!)
            addItemDecoration(itemDecorator)
        }
        binding.upcomingMovieRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchMoreUpcomingMovies()
                }
            }
        })
    }

    private fun handleViewState(viewState: ViewState<MovieResult>) {
        when (viewState) {
            is Loading -> {
                binding.upcomingLoadingProgressBar.visibility = View.VISIBLE
            }
            is Success -> {
                rvAdapter.reloadList(viewState.data.movies)
                rvAdapter.notifyItemInserted(rvAdapter.itemCount - 1)
                binding.upcomingLoadingProgressBar.visibility = View.GONE
            }
            is Error -> {
                binding.upcomingLoadingProgressBar.visibility = View.GONE
                snackbar("Error happened while fetching upcoming movies please try later.",
                    requireView())
            }
            is NoInternetState -> {
                binding.upcomingLoadingProgressBar.visibility = View.GONE
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