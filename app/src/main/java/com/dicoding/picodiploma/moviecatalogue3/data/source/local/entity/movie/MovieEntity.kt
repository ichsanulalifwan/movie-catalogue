package com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = "",

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = 0.0,

    @ColumnInfo(name = "tagline")
    var tagline: String? = "",

    @ColumnInfo(name = "runtime")
    var runtime: Int? = 0,

    @ColumnInfo(name = "status")
    var status: String? = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)
