package com.dicoding.picodiploma.moviecatalogue3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue3.utils.LiveDataTestUtil
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateLocalDummyDetailMovies()[0]
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var detailObserver: Observer<Resource<MovieDetailWithGenre>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(moviesRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie =
            Resource.success(DataDummy.generateDummyDetailMovie(dummyMovie, true))
        val detailMovie = MutableLiveData<Resource<MovieDetailWithGenre>>()
        detailMovie.value = dummyDetailMovie
        `when`(moviesRepository.getDetailMovie(movieId)).thenReturn(detailMovie)

        val movieDeailEntity = LiveDataTestUtil.getValue(viewModel.detailMovie)
        verify(moviesRepository).getDetailMovie(movieId)
        assertNotNull(movieDeailEntity)
        assertEquals(dummyMovie.movieId, movieDeailEntity.data?.mMovie?.movieId)
        assertEquals(dummyMovie.overview, movieDeailEntity.data?.mMovie?.overview)
        assertEquals(dummyMovie.releaseDate, movieDeailEntity.data?.mMovie?.releaseDate)
        assertEquals(dummyMovie.voteAverage, movieDeailEntity.data?.mMovie?.voteAverage)
        assertEquals(dummyMovie.runtime, movieDeailEntity.data?.mMovie?.runtime)
        assertEquals(dummyMovie.tagline, movieDeailEntity.data?.mMovie?.tagline)
        assertEquals(dummyMovie.title, movieDeailEntity.data?.mMovie?.title)
        assertEquals(dummyMovie.posterPath, movieDeailEntity.data?.mMovie?.posterPath)
        assertEquals(dummyMovie.status, movieDeailEntity.data?.mMovie?.status)
        assertEquals(dummyMovie.isFavorite, movieDeailEntity.data?.mMovie?.isFavorite)

        viewModel.detailMovie.observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyDetailMovie)
    }
}