package com.movie.data.network.response.common

abstract class MovieResponse{
    abstract val dates: Dates
    abstract val page: Int
    abstract val results: List<Result>
    abstract val totalPages: Int
    abstract val totalResults: Int
}