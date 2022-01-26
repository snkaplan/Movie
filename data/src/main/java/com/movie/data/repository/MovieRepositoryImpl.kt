package com.movie.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.movie.data.common.coroutine.CoroutineContextProvider
import com.movie.data.common.utils.Connectivity
import com.movie.data.network.MovieApi
import com.movie.data.network.base.getData
import com.movie.data.network.response.movies.MoviesResponse
import com.movie.domain.model.Movie
import com.movie.domain.model.MovieResult
import com.movie.domain.model.Result
import com.movie.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    connectivity: Connectivity,
    contextProvider: CoroutineContextProvider,
) : BaseRepository<MovieResult, MoviesResponse>(connectivity, contextProvider),
    MovieRepository {
    override val upcomingMovies: Result<MovieResult>
        get() = _upcomingMovies
    override val nowPlayingMovies: Result<MovieResult>
        get() = _nowPlayingMovies

    private lateinit var _upcomingMovies: Result<MovieResult>
    private lateinit var _nowPlayingMovies: Result<MovieResult>

    override suspend fun getNowPlayingMovies(page: Int): Result<MovieResult> {
        return if(!this::_nowPlayingMovies.isInitialized){
            val fetchData = fetchData(
                dataProvider = { movieApi.getNowPlayingMovies(page).getData() }
            )
            _nowPlayingMovies = fetchData
            _nowPlayingMovies
        } else {
            _nowPlayingMovies
        }
    }

    override suspend fun getUpcomingMovies(page: Int): Result<MovieResult> {
        return if(!this::_upcomingMovies.isInitialized){
            val fetchData = fetchData(
                dataProvider = { movieApi.getUpcomingMovies(page).getData() }
            )
            _upcomingMovies = fetchData
            _upcomingMovies
        } else {
            upcomingMovies
        }
    }

    override suspend fun refreshNowPlayingMovies(page: Int): Result<MovieResult> {
        return fetchData(
            dataProvider = { movieApi.getUpcomingMovies(page).getData() }
        )
    }

    override suspend fun refreshUpcomingMovies(page: Int): Result<MovieResult> {
        return fetchData(
            dataProvider = { movieApi.getUpcomingMovies(page).getData() }
        )
    }
}