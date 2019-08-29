package org.pursuit.mvpswapi.model

data class Movie(val title: String, val episode_id: Int?) {
    override fun toString(): String {
        return title
    }

    companion object {
        fun EMPTY(): Movie {
            return Movie("", null)
        }
    }
}