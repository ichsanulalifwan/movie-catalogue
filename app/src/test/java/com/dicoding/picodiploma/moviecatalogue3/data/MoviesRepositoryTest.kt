package com.dicoding.picodiploma.moviecatalogue3.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.LocalDataSource
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue3.utils.AppExecutors
import com.dicoding.picodiploma.moviecatalogue3.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue3.utils.LiveDataTestUtil
import com.dicoding.picodiploma.moviecatalogue3.utils.PagedListUtil
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.*

class MoviesRepositoryTest {

    private lateinit var moviesRepository: FakeMoviesRepository
    private val remote = mock(RemoteDataSource::class.java, RETURNS_DEEP_STUBS)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieResponses = DataDummy.generateDummyMovies()
    private val tvResponses = DataDummy.generateDummyTvShow()

    private val detailMovie = DataDummy.generateLocalDummyDetailMovies()[0]
    private val movieId = detailMovie.movieId

    private val detailTv = DataDummy.generateLocalDummyDetailTv()[0]
    private val tvId = detailTv.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        moviesRepository = FakeMoviesRepository(remote, local, appExecutors)
    }

    @Test
    fun getPopularMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getPopularMovie()).thenReturn(dataSourceFactory)
        moviesRepository.getPopularMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieResponses))

        verify(local).getPopularMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getPopularTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getPopularTvShow()).thenReturn(dataSourceFactory)
        moviesRepository.getPopularTvShow()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(tvResponses))

        verify(local).getPopularTvShow()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponses.size, tvEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovieEntity = MutableLiveData<MovieDetailWithGenre>()
        dummyMovieEntity.value =
            DataDummy.generateDummyDetailMovie(detailMovie, false)

        `when`(local.getDetailMovieById(movieId)).thenReturn(dummyMovieEntity)

        val detailMovieEntities =
            LiveDataTestUtil.getValue(moviesRepository.getDetailMovie(movieId))

        verify(local).getDetailMovieById(movieId)
        assertNotNull(detailMovieEntities)
        assertNotNull(detailMovieEntities.data?.mMovie)
        assertNotNull(detailMovieEntities.data?.mGenre)
        assertEquals(detailMovie.movieId, detailMovieEntities.data?.mMovie?.movieId)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvEntity = MutableLiveData<TvDetailWithGenre>()
        dummyTvEntity.value =
            DataDummy.generateDummyDetailTv(detailTv, false)

        `when`(local.getDetailTvById(tvId)).thenReturn(dummyTvEntity)

        val detailTvEntities = LiveDataTestUtil.getValue(moviesRepository.getDetailTvShow(tvId))

        verify(local).getDetailTvById(tvId)
        assertNotNull(detailTvEntities)
        assertNotNull(detailTvEntities.data?.mTv)
        assertNotNull(detailTvEntities.data?.mGenre)
        assertEquals(detailTv.tvId, detailTvEntities.data?.mTv?.tvId)
    }

    @Test
    fun getFavMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        moviesRepository.getFavMovie()

        val favMovieEntities = Resource.success(PagedListUtil.mockPagedList(movieResponses))

        verify(local).getFavoriteMovie()
        assertNotNull(favMovieEntities)
        assertEquals(movieResponses.size, favMovieEntities.data?.size)
    }

    @Test
    fun getFavTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        moviesRepository.getFavTvShow()

        val favTvEntities = Resource.success(PagedListUtil.mockPagedList(tvResponses))

        verify(local).getFavoriteTvShow()
        assertNotNull(favTvEntities)
        assertEquals(movieResponses.size, favTvEntities.data?.size)
    }
}