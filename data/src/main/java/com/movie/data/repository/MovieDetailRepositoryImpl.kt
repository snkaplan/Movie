package com.movie.data.repository

import com.movie.data.common.coroutine.CoroutineContextProvider
import com.movie.data.common.utils.Connectivity
import com.movie.data.network.MovieApi
import com.movie.data.network.base.getData
import com.movie.data.network.response.moviedetail.MovieDetailResponse
import com.movie.domain.model.Movie
import com.movie.domain.model.Result
import com.movie.domain.repository.MovieDetailRepository

class MovieDetailRepositoryImpl(
    private val movieApi: MovieApi,
    connectivity: Connectivity,
    contextProvider: CoroutineContextProvider,
) : BaseRepository<Movie, MovieDetailResponse>(connectivity, contextProvider),
    MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): Result<Movie> {
        return fetchData(
            dataProvider = { movieApi.getMovieDetail(movieId).getData() }
        )
    }
}