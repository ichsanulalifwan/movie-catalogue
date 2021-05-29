package com.dicoding.picodiploma.moviecatalogue3.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.LocalDataSource
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.ApiResponse
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.*
import com.dicoding.picodiploma.moviecatalogue3.utils.AppExecutors
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource

class FakeMoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesDataSource {

    override fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.releaseDate,
                        response.overview,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.firstAirDate,
                        response.overview,
                        response.posterPath
                    )
                    tvList.add(tvShow)
                }
                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieDetailWithGenre>> {
        return object :
            NetworkBoundResource<MovieDetailWithGenre, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieDetailWithGenre> =
                localDataSource.getDetailMovieById(movieId)

            override fun shouldFetch(data: MovieDetailWithGenre?): Boolean =
                data?.mGenre == null || data.mGenre.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: MovieDetailResponse) {
                val movieData = ArrayList<MovieEntity>()
                movieData.add(
                    MovieEntity(
                        movieId,
                        data.title,
                        data.releaseDate,
                        data.overview,
                        data.posterPath,
                        data.voteAverage,
                        data.tagline,
                        data.runtime,
                        data.status
                    )
                )
                localDataSource.insertMovie(movieData)

                val genreList = ArrayList<MovieGenreEntity>()
                for (response in data.genres) {
                    val genre = MovieGenreEntity(
                        movieId,
                        response.id,
                        response.name
                    )
                    genreList.add(genre)
                }
                localDataSource.insertMovieGenre(genreList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvId: Int): LiveData<Resource<TvDetailWithGenre>> {
        return object :
            NetworkBoundResource<TvDetailWithGenre, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvDetailWithGenre> =
                localDataSource.getDetailTvById(tvId)

            override fun shouldFetch(data: TvDetailWithGenre?): Boolean =
                data?.mGenre == null || data.mGenre.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getDetailTvShow(tvId)

            override fun saveCallResult(data: TvShowDetailResponse) {
                val tvData = ArrayList<TvShowEntity>()
                tvData.add(
                    TvShowEntity(
                        tvId,
                        data.name,
                        data.firstAirDate,
                        data.overview,
                        data.posterPath,
                        data.numberOfEpisodes,
                        data.numberOfSeasons,
                        data.type,
                        data.voteAverage,
                        data.tagline,
                        data.status
                    )
                )
                localDataSource.insertTvShow(tvData)

                val genreList = ArrayList<TvGenreEntity>()
                for (response in data.genres) {
                    val genre = TvGenreEntity(
                        tvId,
                        response.id,
                        response.name
                    )
                    genreList.add(genre)
                }
                localDataSource.insertTvShowGenre(genreList)
            }
        }.asLiveData()
    }

    override fun getFavMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setFavMovie(movie, state)
        }

    override fun setFavTvShow(tv: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setFavTvShow(tv, state)
        }
}