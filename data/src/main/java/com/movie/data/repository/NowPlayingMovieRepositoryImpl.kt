package com.movie.data.repository

import com.movie.data.common.coroutine.CoroutineContextProvider
import com.movie.data.common.utils.Connectivity
import com.movie.data.network.MovieApi
import com.movie.data.network.base.getData
import com.movie.data.network.response.NowPlayingResponse
import com.movie.domain.model.NowPlayingMovieInfo
import com.movie.domain.model.Result
import com.movie.domain.repository.NowPlayingMovieRepository

class NowPlayingMovieRepositoryImpl(
    private val movieApi: MovieApi,
    connectivity: Connectivity,
    contextProvider: CoroutineContextProvider,
) : BaseRepository<NowPlayingMovieInfo, NowPlayingResponse>(connectivity, contextProvider),
    NowPlayingMovieRepository {
    override suspend fun getNowPlayingMovies(): Result<NowPlayingMovieInfo> {
        return fetchData(
            dataProvider = { movieApi.getNowPlayingMovies().getData() }
        )
    }
}