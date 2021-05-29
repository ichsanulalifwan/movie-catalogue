package com.dicoding.picodiploma.moviecatalogue3.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity

class FavListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getFavMovie(): LiveData<PagedList<MovieEntity>> = moviesRepository.getFavMovie()

    fun getFavTvShow(): LiveData<PagedList<TvShowEntity>> = moviesRepository.getFavTvShow()
}