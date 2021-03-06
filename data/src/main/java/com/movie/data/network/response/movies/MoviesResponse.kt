package com.movie.data.network.response.movies


import com.google.gson.annotations.SerializedName
import com.movie.data.network.base.DomainMapper
import com.movie.data.network.response.common.Dates
import com.movie.data.network.response.common.MovieResponse
import com.movie.data.network.response.common.Result
import com.movie.domain.model.Movie
import com.movie.domain.model.MovieResult

data class MoviesResponse(
    override val dates: Dates,
    override val page: Int,
    override val results: List<Result>,
    @SerializedName("total_pages")
    override val totalPages: Int,
    @SerializedName("total_results")
    override val totalResults: Int,
) : MovieResponse(), DomainMapper<MovieResult> {
    override fun mapToDomainModel(): MovieResult {
        val movies = arrayListOf<Movie>()
        for (result in results) {
            movies.add(Movie(result.id, result.title,
                result.releaseDate,
                result.overview,
                result.posterPath,
                result.voteAverage,
                null))
        }
        return MovieResult(page, totalPages, totalResults, movies)
    }
}