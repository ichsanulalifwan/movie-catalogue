package com.dicoding.picodiploma.moviecatalogue3.ui.favourite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.databinding.FragmentFavListBinding
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.MovieAdapter
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.TvShowAdapter
import com.dicoding.picodiploma.moviecatalogue3.ui.detail.DetailMovieActivity
import com.dicoding.picodiploma.moviecatalogue3.ui.detail.DetailTvShowActivity
import com.dicoding.picodiploma.moviecatalogue3.viewmodel.ViewModelFactory

class FavListFragment : Fragment() {

    private lateinit var binding: FragmentFavListBinding
    private lateinit var viewModel: FavListViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavListViewModel::class.java]

            movieAdapter = MovieAdapter()
            tvAdapter = TvShowAdapter()

            setupRecyclerView()
            showLoading(true)

            when (index) {
                0 -> showFavMovie()
                1 -> showFavTv()
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvFav) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun showFavMovie() {
        binding.rvFav.adapter = movieAdapter
        viewModel.getFavMovie().observe(viewLifecycleOwner, { movie ->
            movieAdapter.submitList(movie)
        })
        showLoading(false)

        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onMoviesClicked(movies: MovieEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movies.movieId)
                startActivity(intent)
            }
        })
    }

    private fun showFavTv() {
        binding.rvFav.adapter = tvAdapter
        viewModel.getFavTvShow().observe(viewLifecycleOwner, { tv ->
            tvAdapter.submitList(tv)
        })
        showLoading(false)

        tvAdapter.setOnItemClickListener(object : TvShowAdapter.OnItemClickListener {
            override fun onTvClicked(tv: TvShowEntity) {
                val intent = Intent(context, DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.EXTRA_TV_ID, tv.tvId)
                startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): FavListFragment {
            val fragment = FavListFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }
}