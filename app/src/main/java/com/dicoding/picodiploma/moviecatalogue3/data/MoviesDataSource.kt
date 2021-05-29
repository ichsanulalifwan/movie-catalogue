package com.dicoding.picodiploma.moviecatalogue3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource

interface MoviesDataSource {

    fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieDetailWithGenre>>

    fun getDetailTvShow(tvId: Int): LiveData<Resource<TvDetailWithGenre>>

    fun getFavMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)

    fun setFavTvShow(tv: TvShowEntity, state: Boolean)
}