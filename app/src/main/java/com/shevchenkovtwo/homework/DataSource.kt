package com.shevchenkovtwo.homework

class DataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                imageMask = R.drawable.mask_avengers,
                imageForList = R.drawable.avengers,
                favorite = false,
                name = "Avengers: End Game",
                tags = "Action, Adventure, Drama",
                duration = "137 min",
                rating = 4f,
                reviews = "125 Reviews",
                pg = "13+",
                storyline = R.string.movie_description,
                getActors()
            ),
            Movie(
                imageForList = R.drawable.tenet,
                favorite = true,
                name = "Tenet",
                tags = "Action, Sci-Fi, Thriller ",
                duration = "97 min",
                rating = 5f,
                reviews = "98 Reviews",
                pg = "16+"
            ),
            Movie(
                imageForList = R.drawable.black_widow,
                favorite = false,
                name = "Black Widow",
                tags = "Action, Adventure, Sci-Fi",
                duration = "102 min",
                rating = 4f,
                reviews = "36 Reviews",
                pg = "13+",
            ),
            Movie(
                imageForList = R.drawable.wonder_woman,
                favorite = false,
                name = "Wonder Woman 1984",
                tags = "Action, Adventure, Fantasy",
                duration = "120 min",
                rating = 5f,
                reviews = "74 Reviews",
                pg = "13+"
            )
        )
    }

    private fun getActors(): List<Actor> {
        return listOf(
            Actor(R.drawable.robert, R.string.robert_downey_jr),
            Actor(R.drawable.chrisevans, R.string.chris_evans),
            Actor(R.drawable.mark, R.string.mark_ruffalo),
            Actor(R.drawable.chrishemsworth, R.string.chris_hemsworth)
        )
    }
}