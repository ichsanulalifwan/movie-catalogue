package com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("results")
    val results: List<TvShowResultsItem>
)

data class TvShowResultsItem(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String
)
