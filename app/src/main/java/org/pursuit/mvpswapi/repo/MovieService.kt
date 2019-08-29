package org.pursuit.mvpswapi.repo

import io.reactivex.Single
import org.pursuit.mvpswapi.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("films/")
    fun getMovies(): Single<MovieResponse>
}