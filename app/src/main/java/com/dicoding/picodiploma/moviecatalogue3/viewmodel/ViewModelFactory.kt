package com.dicoding.picodiploma.moviecatalogue3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.moviecatalogue3.di.Injection
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.ui.detail.DetailMovieViewModel
import com.dicoding.picodiploma.moviecatalogue3.ui.detail.DetailTvShowViewModel
import com.dicoding.picodiploma.moviecatalogue3.ui.favourite.FavListViewModel
import com.dicoding.picodiploma.moviecatalogue3.ui.movie.MovieViewModel
import com.dicoding.picodiploma.moviecatalogue3.ui.tvshow.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val repository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(repository) as T
            }

            modelClass.isAssignableFrom(FavListViewModel::class.java) -> {
                FavListViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}