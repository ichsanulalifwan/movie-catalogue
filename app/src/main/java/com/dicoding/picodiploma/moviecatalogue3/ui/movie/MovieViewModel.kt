package com.dicoding.picodiploma.moviecatalogue3.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>> =
        moviesRepository.getPopularMovie()
}