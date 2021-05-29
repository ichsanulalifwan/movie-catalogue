package com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieDetailWithGenre(
    @Embedded
    var mMovie: MovieEntity,

    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    var mGenre: List<MovieGenreEntity>
)
