package org.pursuit.mvpswapi.presentation

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import org.pursuit.mvpswapi.R
import org.pursuit.mvpswapi.contract.MainContract
import org.pursuit.mvpswapi.model.Movie
import org.pursuit.mvpswapi.repo.MoviesRepo

class MainActivity : AppCompatActivity(), MainContract.View {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter: MainContract.Presenter = MainPresenter(this, MoviesRepo().getService())
        disposables.add(presenter.getMovies())
    }

    override fun showMovies(movies: List<Movie>?) {
        var allTitles = ""

        movies?.let {
            for (item in it) {
                allTitles += "\n $item"
            }
        }
        findViewById<TextView>(R.id.mainTextView).text = allTitles
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

}
