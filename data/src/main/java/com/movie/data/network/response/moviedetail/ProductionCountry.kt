package com.movie.data.network.response.moviedetail


import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String
)