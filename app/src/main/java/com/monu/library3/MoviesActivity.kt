package com.monu.library3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.monu.library3.Adapter.Movies_adapter
import com.monu.library3.model.Movies
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager

    val movielist = arrayListOf<Movies>()
    lateinit var recyclerAdapter: Movies_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        supportActionBar?.setTitle("Entertainment")

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=60ca73ba7ee6ef92cc48a4358068aaff"

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
            println("Response is: $it" )

            val data = it.getJSONArray("results")
            for(i in 0 until data.length()){
                val movieJsonObject = data.getJSONObject(i)
                val image: String = movieJsonObject.getString("poster_path")
                val url : String = "https://image.tmdb.org/t/p/w500"
                val image2 : String = image.replace("\\","/https://image.tmdb.org/t/p/w500")
                val image3 : String = url.plus(image2)
                val movieObject = Movies(
                        movieJsonObject.getString("title"),
                        movieJsonObject.getString("vote_average"),
                        image3,
                        movieJsonObject.getString("overview"),
                        movieJsonObject.getString("release_date")
                )
                movielist.add(movieObject)
                Log.d("Image",image3)

                layoutManager = LinearLayoutManager(this)
                recyclerAdapter = Movies_adapter(this,movielist)

                recyclerview1.adapter = recyclerAdapter
                recyclerview1.layoutManager = layoutManager

            }
        },Response.ErrorListener {
            println("Response is: $it")
        })
        {/*
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "xyz"
                return headers
            } */
        }

        queue.add(jsonObjectRequest)
    }
}