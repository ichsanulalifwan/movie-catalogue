package com.dicoding.picodiploma.moviecatalogue3.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource

class TvShowViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> =
        repository.getPopularTvShow()
}