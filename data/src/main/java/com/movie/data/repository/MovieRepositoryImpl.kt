package com.movie.data.repository

import com.movie.data.common.coroutine.CoroutineContextProvider
import com.movie.data.common.utils.Connectivity
import com.movie.data.network.MovieApi
import com.movie.data.network.base.getData
import com.movie.data.network.response.movies.MoviesResponse
import com.movie.domain.model.MovieResult
import com.movie.domain.model.Result
import com.movie.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    connectivity: Connectivity,
    contextProvider: CoroutineContextProvider,
) : BaseRepository<MovieResult, MoviesResponse>(connectivity, contextProvider),
    MovieRepository {
    override suspend fun getNowPlayingMovies(page: Int): Result<MovieResult> {
        return fetchData(
            dataProvider = { movieApi.getNowPlayingMovies(page).getData() }
        )
    }

    override suspend fun getUpcomingMovies(page: Int): Result<MovieResult> {
        return fetchData(
            dataProvider = { movieApi.getUpcomingMovies(page).getData() }
        )
    }
}