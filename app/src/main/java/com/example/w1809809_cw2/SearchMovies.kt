package com.example.w1809809_cw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchMovies : AppCompatActivity() {
    private var title = ""
    private var year = ""
    private var rated = ""
    private var released = ""
    private var runtime = ""
    private var genre = ""
    private var director = ""
    private var writer = ""
    private var actors = ""
    private var plot = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        val search = findViewById<EditText>(R.id.editTxt)
        val retrieveMovie = findViewById<Button>(R.id.btn1)
        val saveMovie = findViewById<Button>(R.id.btn2)
        val searchText = search.text

        retrieveMovie.setOnClickListener {
            var stb = StringBuilder()
            val url_string = "https://www.omdbapi.com/?t=$searchText&apikey=89ca4841"
            val url = URL(url_string)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            runBlocking {
                launch {
                    // run the code of the coroutine in a new thread
                    withContext(Dispatchers.IO) {
                        val bf = BufferedReader(InputStreamReader(con.inputStream))
                        var line: String? = bf.readLine()
                        while (line != null) {
                            stb.append(line + "\n")
                            line = bf.readLine()
                        }
                    }
                    parseJSON(stb)
                }
            }
        }
        saveMovie.setOnClickListener {
            val db = Room.databaseBuilder(
                this, MovieDB::class.java,
                "MovieDB"
            ).allowMainThreadQueries().build()
            val movieDao = db.movieDao()
            runBlocking {
                launch {
                    val movie1 = Movie(
                        title,//your entities
                        year,
                        rated,
                        released,
                        runtime,
                        genre,
                        director,
                        writer,
                        actors,
                        plot
                    )
                    movieDao.insertMovies(movie1)
                }
            }
        }

    }

    private fun parseJSON(stb: java.lang.StringBuilder) {
        val display = findViewById<TextView>(R.id.textView)// text view for display result

        val json = JSONObject(stb.toString())

        val allMovies = java.lang.StringBuilder()


        title = json.getString("Title")
        year = json.getString("Year")
        rated = json.getString("Rated")
        released = json.getString("Released")
        runtime = json.getString("Runtime")
        genre = json.getString("Genre")
        director = json.getString("Director")
        writer = json.getString("Writer")
        actors = json.getString("Actors")
        plot = json.getString("Plot")



        allMovies.append("---------------------------------------------------------\n\nTitle :\"$title\"\n")
        allMovies.append("\nYear :\"$year\"\n")
        allMovies.append("\nRated :\"$rated\"\n")
        allMovies.append("\nReleased :\"$released\"\n")
        allMovies.append("\nRuntime :\"$runtime\"\n")
        allMovies.append("\nGenre :\"$genre\"\n")
        allMovies.append("\nDirector :\"$director\"\n")
        allMovies.append("\nWriter :\"$writer\"\n")
        allMovies.append("\nActors :\"$actors\"\n")
        allMovies.append("\nPlot :\"$plot\"\n\n---------------------------------------------------------")

        display.text = ""
        display.text = allMovies
    }
}