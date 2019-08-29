package org.pursuit.mvpswapi.contract

import io.reactivex.disposables.Disposable
import org.pursuit.mvpswapi.model.Movie

interface MainContract {
    interface View {
        fun showMovies(movies: List<Movie>?)
        fun showError()
    }

    interface Presenter {
        fun getMovies(): Disposable
    }
}