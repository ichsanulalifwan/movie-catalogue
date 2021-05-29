package com.dicoding.picodiploma.moviecatalogue3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity

@Dao
interface MoviesDao {

    // Movie
    @Query("SELECT * FROM movie_entities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities where isFavorite = 1")
    fun getFavMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieDetailWithGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieGenre(genre: List<MovieGenreEntity>)

    // Tv Show
    @Query("SELECT * FROM tv_entities")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_entities where isFavorite = 1")
    fun getFavTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tv_entities WHERE tvId = :tvId")
    fun getDetailTvById(tvId: Int): LiveData<TvDetailWithGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tv: List<TvShowEntity>)

    @Update
    fun updateTvShow(tv: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvGenre(genre: List<TvGenreEntity>)
}