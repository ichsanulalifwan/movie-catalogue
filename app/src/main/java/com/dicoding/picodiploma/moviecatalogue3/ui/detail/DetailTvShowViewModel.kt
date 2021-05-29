package com.dicoding.picodiploma.moviecatalogue3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource

class DetailTvShowViewModel(private val repository: MoviesRepository) : ViewModel() {

    val tvId = MutableLiveData<Int>()

    var detailTv: LiveData<Resource<TvDetailWithGenre>> =
        Transformations.switchMap(tvId) { tvId ->
            repository.getDetailTvShow(tvId)
        }

    fun setSelectedTvShow(tvId: Int) {
        this.tvId.value = tvId
    }

    fun setTvFavorite() {
        val tvResource = detailTv.value
        if (tvResource != null) {
            val tv = tvResource.data
            if (tv != null) {
                val tvEntity = tv.mTv
                val newState = !tvEntity.isFavorite
                repository.setFavTvShow(tvEntity, newState)
            }
        }
    }
}