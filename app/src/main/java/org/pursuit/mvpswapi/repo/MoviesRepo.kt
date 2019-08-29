package org.pursuit.mvpswapi.repo

class MoviesRepo {
    private val service = SwapiRetrofit.swapiRetrofit.create(MovieService::class.java)

    fun getService(): MovieService {
        return service
    }
}