package com.movie.data.network

import com.movie.data.network.response.moviedetail.MovieDetailResponse
import com.movie.data.network.response.movies.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("{movie-id}")
    suspend fun getMovieDetail(@Path("movie-id") page: Int): Response<MovieDetailResponse>
}