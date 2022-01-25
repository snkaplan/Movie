package com.movie.data.network

import com.movie.data.network.response.nowplaying.NowPlayingResponse
import com.movie.data.network.response.upcoming.UpcomingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): Response<NowPlayingResponse>

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): Response<UpcomingMovies>
}