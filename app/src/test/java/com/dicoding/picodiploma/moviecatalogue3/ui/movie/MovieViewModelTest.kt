package com.dicoding.picodiploma.moviecatalogue3.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(moviesRepository)
    }

    @Test
    fun getPopularMovie() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(6)

        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummyMovies
        `when`(moviesRepository.getPopularMovie()).thenReturn(movie)

        val movieEntities = viewModel.getPopularMovie().value?.data
        verify(moviesRepository).getPopularMovie()
        assertNotNull(movieEntities)
        assertEquals(6, movieEntities?.size)

        viewModel.getPopularMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}