package org.pursuit.mvpswapi.presentation

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.pursuit.mvpswapi.contract.MainContract
import org.pursuit.mvpswapi.model.MovieResponse
import org.pursuit.mvpswapi.repo.MovieService

class MainPresenter(val view: MainContract.View, private val api: MovieService) :
    MainContract.Presenter {

    override fun getMovies(): Disposable {
        return api.getMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!it.results.isEmpty()) {
                    view.showMovies(it.results)
                } else {
                    view.showError()
                }
            },
                { view.showError() })
    }
}