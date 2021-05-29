package com.dicoding.picodiploma.moviecatalogue3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.room.MoviesDao

class LocalDataSource private constructor(private val moviesDao: MoviesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: MoviesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }

    // Movie
    fun getPopularMovie(): DataSource.Factory<Int, MovieEntity> = moviesDao.getMovies()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = moviesDao.getFavMovie()

    fun getDetailMovieById(movieId: Int): LiveData<MovieDetailWithGenre> =
        moviesDao.getDetailMovieById(movieId)

    fun insertMovie(movie: List<MovieEntity>) = moviesDao.insertMovie(movie)

    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        moviesDao.updateMovie(movie)
    }

    fun insertMovieGenre(genres: List<MovieGenreEntity>) = moviesDao.insertMovieGenre(genres)

    // Tv Show
    fun getPopularTvShow(): DataSource.Factory<Int, TvShowEntity> = moviesDao.getTvShow()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = moviesDao.getFavTvShow()

    fun getDetailTvById(tvId: Int): LiveData<TvDetailWithGenre> =
        moviesDao.getDetailTvById(tvId)

    fun insertTvShow(tvShow: List<TvShowEntity>) = moviesDao.insertTvShow(tvShow)

    fun setFavTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        moviesDao.updateTvShow(tvShow)
    }

    fun insertTvShowGenre(genres: List<TvGenreEntity>) = moviesDao.insertTvGenre(genres)
}