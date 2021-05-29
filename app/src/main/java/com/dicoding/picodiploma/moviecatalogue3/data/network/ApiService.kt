package com.dicoding.picodiploma.moviecatalogue3.data.network

import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.TvShowResponse
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.MovieDetailResponse
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.TvShowDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("tv/popular")
    fun getTvShow(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<TvShowDetailResponse>
}