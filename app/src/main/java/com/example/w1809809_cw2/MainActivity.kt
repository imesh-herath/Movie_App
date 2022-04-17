package com.example.w1809809_cw2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var addMoviesToDBBtn = findViewById<Button>(R.id.addBtn)
        var searchMoviesBtn = findViewById<Button>(R.id.searchMovieBtn)
        var searchActorsBtn = findViewById<Button>(R.id.searchActorBtn)

        addMoviesToDBBtn.setOnClickListener {
            val addMoviesIntent = Intent (this, AddMoviesToDB::class.java)
            startActivity(addMoviesIntent)
        }

        searchMoviesBtn.setOnClickListener {
            val searchMoviesIntent = Intent (this, SearchMovies::class.java)
            startActivity(searchMoviesIntent)
        }
    }

}