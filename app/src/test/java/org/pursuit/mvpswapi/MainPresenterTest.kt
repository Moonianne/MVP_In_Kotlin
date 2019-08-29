package org.pursuit.mvpswapi

import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.pursuit.mvpswapi.contract.MainContract
import org.pursuit.mvpswapi.model.Movie
import org.pursuit.mvpswapi.model.MovieResponse
import org.pursuit.mvpswapi.presentation.MainPresenter
import org.pursuit.mvpswapi.repo.MovieService
import java.util.*
import kotlin.collections.ArrayList

class MainPresenterTest {
    private val view: MainContract.View = mock(MainContract.View::class.java)
    private val api: MovieService = mock(MovieService::class.java)
    private val presenter: MainContract.Presenter = MainPresenter(view, api)

    @Before
    fun setup() {
        RxJavaPlugins
            .setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins
            .setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun GivenListIsEmpty_WhenGetMoviesIsCalled_ThenShowError() {
        //Given
        val response = MovieResponse(0, Collections.emptyList())
        `when`(api.getMovies()).thenReturn(Single.just(response))

        //When
        presenter.getMovies()

        //Then
        verify(view).showError()
        verify(view, never()).showMovies(anyList())
    }

    @Test
    fun GivenListHas4Items_WhenGetMoviesIsCalled_ThenShowMovies() {
        //Given
        val movieList = ArrayList<Movie>()
        movieList.add(Movie.EMPTY())
        movieList.add(Movie.EMPTY())
        movieList.add(Movie.EMPTY())
        movieList.add(Movie.EMPTY())
        val response = MovieResponse(movieList.size, movieList)

        `when`(api.getMovies()).thenReturn(Single.just(response))

        // When
        presenter.getMovies()

        // Then
        verify(view).showMovies(movieList)
        verify(view, never()).showError()
    }

    @Test
    fun GivenListIsNull_WhenGetMoviesIsCalled_ThenShowError() {
        // Given
        `when`(api.getMovies()).thenReturn(Single.error(Throwable()))

        // When
        presenter.getMovies()

        // Then
        verify(view).showError()
        verify(view, never()).showMovies(null)
    }
}