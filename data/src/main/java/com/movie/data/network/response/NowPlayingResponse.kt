package com.movie.data.network.response


import com.google.gson.annotations.SerializedName
import com.movie.data.network.base.DomainMapper
import com.movie.domain.model.MovieInfo
import com.movie.domain.model.NowPlayingMovieInfo
import java.util.*

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
) : DomainMapper<NowPlayingMovieInfo> {
    override fun mapToDomainModel(): NowPlayingMovieInfo {
        val movies = arrayListOf<MovieInfo>()
        for(result in results){
            movies.add(MovieInfo(result.title, Date(), result.overview, result.posterPath, result.backdropPath))
        }
        return NowPlayingMovieInfo(page, totalPages, totalResults, movies)
    }
}