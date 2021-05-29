package com.dicoding.picodiploma.moviecatalogue3.ui.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavListViewModelTest {

    private lateinit var viewModel: FavListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var tvPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavListViewModel(moviesRepository)
    }

    @Test
    fun getFavMovie() {
        val dummyFavMovie = moviePagedList
        `when`(dummyFavMovie.size).thenReturn(6)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyFavMovie
        `when`(moviesRepository.getFavMovie()).thenReturn(movies)

        val favMovieEntities = viewModel.getFavMovie().value
        verify(moviesRepository).getFavMovie()
        assertNotNull(favMovieEntities)
        assertEquals(6, favMovieEntities?.size)

        viewModel.getFavMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyFavMovie)
    }

    @Test
    fun getFavTvShow() {
        val dummyFavTv = tvPagedList
        `when`(dummyFavTv.size).thenReturn(6)

        val tv = MutableLiveData<PagedList<TvShowEntity>>()
        tv.value = dummyFavTv
        `when`(moviesRepository.getFavTvShow()).thenReturn(tv)

        val favTvEntities = viewModel.getFavTvShow().value
        verify(moviesRepository).getFavTvShow()
        assertNotNull(favTvEntities)
        assertEquals(6, favTvEntities?.size)

        viewModel.getFavTvShow().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyFavTv)
    }
}