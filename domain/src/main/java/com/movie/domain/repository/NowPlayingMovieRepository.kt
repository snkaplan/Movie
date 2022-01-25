package com.movie.domain.repository

import com.movie.domain.model.NowPlayingMovieInfo
import com.movie.domain.model.Result

interface NowPlayingMovieRepository {
    suspend fun getNowPlayingMovies(): Result<NowPlayingMovieInfo>
}