package com.dicoding.picodiploma.moviecatalogue3.di

import android.content.Context
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.LocalDataSource
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.room.MoviesDatabase
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue3.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MoviesRepository {

        val database = MoviesDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesDao())
        val appExecutors = AppExecutors()

        return MoviesRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}