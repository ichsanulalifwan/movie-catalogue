package com.dicoding.picodiploma.moviecatalogue3.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.MovieAdapter.Companion.IMAGE_PREFIX
import com.dicoding.picodiploma.moviecatalogue3.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.moviecatalogue3.R
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.databinding.ActivityDetailMovieBinding
import com.dicoding.picodiploma.moviecatalogue3.model.GenreData
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.GenresAdapter
import com.dicoding.picodiploma.moviecatalogue3.utils.DataMapper
import com.dicoding.picodiploma.moviecatalogue3.vo.Status

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var genresAdapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        genresAdapter = GenresAdapter()

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE_ID)
            viewModel.setSelectedMovie(movieId)

            viewModel.detailMovie.observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            if (movie.data != null) {
                                populateData(movie.data.mMovie)
                                getGenres(DataMapper.mapMovieGenreEntityToModel(movie.data.mGenre))
                                setFavouriteState(movie.data.mMovie.isFavorite)
                                onFavButtonClicked(movie.data.mMovie)
                                showLoading(false)
                            }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(
                                applicationContext,
                                "Something Wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
    }

    private fun populateData(movie: MovieEntity) {
        binding.apply {
            tvOverview.text = movie.overview
            tvRelase.text = movie.releaseDate
            tvVote.text = movie.voteAverage.toString()
            tvRuntime.text = movie.runtime.toString()
            tvTagline.text = movie.tagline
            tvTitleMovie.text = movie.title
            tvStatus.text = movie.status

            Glide.with(this@DetailMovieActivity)
                .load(IMAGE_PREFIX + movie.posterPath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.movie_poster))
                .into(imgPosterMovie)
        }
    }

    private fun getGenres(genres: List<GenreData>) {
        genresAdapter.setData(genres)
        with(binding.rvGenres) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = genresAdapter
        }
    }

    private fun onFavButtonClicked(data: MovieEntity) {
        binding.btnFav.setOnClickListener {
            viewModel.setMovieFavorite()
            if (!data.isFavorite) Toast.makeText(
                this,
                data.title + " " + "Added to Favorite",
                Toast.LENGTH_SHORT
            ).show()
            else Toast.makeText(
                this,
                data.title + " " + "Deleted from Favorite",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setFavouriteState(state: Boolean) {
        if (state) {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }
}