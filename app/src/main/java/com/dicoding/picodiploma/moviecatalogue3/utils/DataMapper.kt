package com.dicoding.picodiploma.moviecatalogue3.utils

import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.model.GenreData

object DataMapper {

    fun mapMovieGenreEntityToModel(input: List<MovieGenreEntity>): List<GenreData> =
        input.map {
            GenreData(
                name = it.genreName,
                id = it.genreId
            )
        }

    fun mapTvGenreEntityToModel(input: List<TvGenreEntity>): List<GenreData> =
        input.map {
            GenreData(
                name = it.genreName,
                id = it.genreId
            )
        }
}