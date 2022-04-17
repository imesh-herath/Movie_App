package com.example.w1809809_cw2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
        @Query("SELECT * FROM movie")
        suspend fun getAll(): List<Movie>
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertMovies(vararg movie: Movie)
        @Insert
        suspend fun insertAll(vararg movies: Movie)
}
