package com.dicoding.picodiploma.moviecatalogue3.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.databinding.FragmentMovieBinding
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.MovieAdapter
import com.dicoding.picodiploma.moviecatalogue3.ui.detail.DetailMovieActivity
import com.dicoding.picodiploma.moviecatalogue3.ui.detail.DetailMovieActivity.Companion.EXTRA_MOVIE_ID
import com.dicoding.picodiploma.moviecatalogue3.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.moviecatalogue3.vo.Status

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()
            setupRecyclerView()
            showLoading(true)

            viewModel.getPopularMovie().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            movieAdapter.submitList(movie.data)
                            showLoading(false)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            onMovieSelected()
        }
    }

    private fun setupRecyclerView() {
        with(fragmentMovieBinding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun onMovieSelected() {
        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onMoviesClicked(movies: MovieEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(EXTRA_MOVIE_ID, movies.movieId)
                startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.progressBar.visibility = View.GONE
        }
    }
}