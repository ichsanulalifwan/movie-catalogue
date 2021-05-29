package com.dicoding.picodiploma.moviecatalogue3.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(moviesRepository)
    }

    @Test
    fun getPopularTvShow() {
        val dummyTv = Resource.success(pagedList)
        Mockito.`when`(dummyTv.data?.size).thenReturn(6)

        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTv
        Mockito.`when`(moviesRepository.getPopularTvShow()).thenReturn(tvShow)

        val tvEntities = viewModel.getPopularTvShow().value?.data
        Mockito.verify(moviesRepository).getPopularTvShow()
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(6, tvEntities?.size)

        viewModel.getPopularTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }
}