package com.dicoding.picodiploma.moviecatalogue3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.moviecatalogue3.data.MoviesRepository
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue3.utils.LiveDataTestUtil
import com.dicoding.picodiploma.moviecatalogue3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTv = DataDummy.generateLocalDummyDetailTv()[0]
    private val tvId = dummyTv.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: MoviesRepository

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvDetailWithGenre>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(tvRepository)
        viewModel.setSelectedTvShow(tvId)
    }

    @Test
    fun getDetailTv() {
        val dummyDetailTv =
            Resource.success(DataDummy.generateDummyDetailTv(dummyTv, true))
        val detailTv = MutableLiveData<Resource<TvDetailWithGenre>>()
        detailTv.value = dummyDetailTv
        `when`(tvRepository.getDetailTvShow(tvId)).thenReturn(detailTv)

        val tvEntity = LiveDataTestUtil.getValue(viewModel.detailTv)
        verify(tvRepository).getDetailTvShow(tvId)
        assertNotNull(tvEntity)
        assertEquals(dummyTv.tvId, tvEntity.data?.mTv?.tvId)
        assertEquals(dummyTv.firstAirDate, tvEntity.data?.mTv?.firstAirDate)
        assertEquals(dummyTv.overview, tvEntity.data?.mTv?.overview)
        assertEquals(dummyTv.numberOfEpisodes, tvEntity.data?.mTv?.numberOfEpisodes)
        assertEquals(dummyTv.type, tvEntity.data?.mTv?.type)
        assertEquals(dummyTv.posterPath, tvEntity.data?.mTv?.posterPath)
        assertEquals(dummyTv.isFavorite, tvEntity.data?.mTv?.isFavorite)
        assertEquals(dummyTv.voteAverage, tvEntity.data?.mTv?.voteAverage)
        assertEquals(dummyTv.name, tvEntity.data?.mTv?.name)
        assertEquals(dummyTv.tagline, tvEntity.data?.mTv?.tagline)
        assertEquals(dummyTv.numberOfSeasons, tvEntity.data?.mTv?.numberOfSeasons)
        assertEquals(dummyTv.status, tvEntity.data?.mTv?.status)

        viewModel.detailTv.observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyDetailTv)
    }
}