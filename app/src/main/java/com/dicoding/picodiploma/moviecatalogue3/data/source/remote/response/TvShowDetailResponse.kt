package com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @field:SerializedName("status")
    val status: String
)
