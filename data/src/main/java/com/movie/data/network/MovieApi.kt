package com.movie.data.network

import com.movie.data.network.response.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(): Response<NowPlayingResponse>
}