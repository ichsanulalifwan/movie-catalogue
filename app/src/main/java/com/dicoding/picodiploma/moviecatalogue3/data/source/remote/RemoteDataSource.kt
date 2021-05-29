package com.dicoding.picodiploma.moviecatalogue3.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.moviecatalogue3.BuildConfig
import com.dicoding.picodiploma.moviecatalogue3.data.network.ApiConfig
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.*
import com.dicoding.picodiploma.moviecatalogue3.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        private const val API_KEY = BuildConfig.Api_Key
        private const val TAG = "RemoteDataSource"

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getPopularMovie(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        ApiConfig.getApiService().getMovie(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.value =
                        ApiResponse.success(response.body()?.results as List<MovieResultsItem>)

                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movies
    }

    fun getPopularTvShow(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        EspressoIdlingResource.increment()
        val tvShow = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        ApiConfig.getApiService().getTvShow(API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    tvShow.value =
                        ApiResponse.success(response.body()?.results as List<TvShowResultsItem>)

                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return tvShow
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        val detailMovie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)
            .enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        detailMovie.value =
                            ApiResponse.success(response.body() as MovieDetailResponse)

                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                            EspressoIdlingResource.decrement()
                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        return detailMovie
    }

    fun getDetailTvShow(tvId: Int): LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResource.increment()
        val detailTvShow = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        ApiConfig.getApiService().getDetailTvShow(tvId, API_KEY)
            .enqueue(object : Callback<TvShowDetailResponse> {
                override fun onResponse(
                    call: Call<TvShowDetailResponse>,
                    response: Response<TvShowDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        detailTvShow.value =
                            ApiResponse.success(response.body() as TvShowDetailResponse)

                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                            EspressoIdlingResource.decrement()
                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        return detailTvShow
    }
}