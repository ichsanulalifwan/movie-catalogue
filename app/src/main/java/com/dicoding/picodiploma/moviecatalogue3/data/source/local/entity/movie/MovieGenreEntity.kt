package com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie

import androidx.annotation.NonNull
import androidx.room.*

@Entity(
    tableName = "movie_genre_entities",
    primaryKeys = ["genreId", "movieId"],
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"]
    )],
    indices = [Index(value = ["genreId"]),
        Index(value = ["movieId"])]
)
data class MovieGenreEntity(
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @NonNull
    @ColumnInfo(name = "genreId")
    var genreId: Int,

    @NonNull
    @ColumnInfo(name = "genre_name")
    var genreName: String
)
