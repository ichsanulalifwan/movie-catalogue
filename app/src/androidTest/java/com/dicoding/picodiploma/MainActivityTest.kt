package com.dicoding.picodiploma

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.picodiploma.moviecatalogue3.ui.main.MainActivity
import com.dicoding.picodiploma.moviecatalogue3.R
import com.dicoding.picodiploma.moviecatalogue3.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue3.utils.EspressoIdlingResource
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_title_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_posterMovie))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_relase))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_vote))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvGenres))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_status))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_runtime))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(AllOf.allOf(isDisplayed(), withText("Tv Shows")))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvShow))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(AllOf.allOf(isDisplayed(), withText("Tv Shows")))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_title_tvShow))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_posterTv))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_first_air))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_season))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_episode))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_voteTv))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvGenres))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_statusTv))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun loadFavorite() {
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.btn_fav))
            .perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActions.pressBack())
        Espresso.onView(AllOf.allOf(isDisplayed(), withText("Favorite")))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_fav))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_title_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_posterMovie))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvGenres))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.btn_fav))
            .perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActions.pressBack())
    }
}