package com.dicoding.picodiploma.moviecatalogue3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource

class DetailMovieViewModel(private val repository: MoviesRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    var detailMovie: LiveData<Resource<MovieDetailWithGenre>> =
        Transformations.switchMap(movieId) { movieId ->
            repository.getDetailMovie(movieId)
        }

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setMovieFavorite() {
        val movieResource = detailMovie.value
        if (movieResource != null) {
            val movie = movieResource.data
            if (movie != null) {
                val movieEntity = movie.mMovie
                val newState = !movieEntity.isFavorite
                repository.setFavMovie(movieEntity, newState)
            }
        }
    }
}