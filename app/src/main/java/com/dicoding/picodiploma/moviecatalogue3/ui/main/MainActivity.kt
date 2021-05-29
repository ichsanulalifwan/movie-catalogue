package com.dicoding.picodiploma.moviecatalogue3.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dicoding.picodiploma.moviecatalogue3.R
import com.dicoding.picodiploma.moviecatalogue3.databinding.ActivityMainBinding
import com.dicoding.picodiploma.moviecatalogue3.ui.favourite.FavouriteFragment
import com.dicoding.picodiploma.moviecatalogue3.ui.movie.MovieFragment
import com.dicoding.picodiploma.moviecatalogue3.ui.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var movieFragment: MovieFragment
    private lateinit var tvShowFragment: TvShowFragment
    private lateinit var favouriteFragment: FavouriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportActionBar?.elevation = 0f
        title = "Movie Catalogue"

        movieFragment = MovieFragment()
        tvShowFragment = TvShowFragment()
        favouriteFragment = FavouriteFragment()

        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        makeCurrentFragment(movieFragment)

        bottomnav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_movie -> makeCurrentFragment(movieFragment)
                R.id.nav_tvShow -> makeCurrentFragment(tvShowFragment)
                R.id.nav_favorite -> makeCurrentFragment(favouriteFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
}