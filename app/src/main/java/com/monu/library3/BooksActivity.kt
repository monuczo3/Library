package com.monu.library3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.monu.library3.Adapter.Books_adapter
import com.monu.library3.Adapter.Movies_adapter
import com.monu.library3.model.Books
import com.monu.library3.model.Movies
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.activity_movies.*

class BooksActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager

    val bookList = arrayListOf<Books>()
    lateinit var recyclerAdapter: Books_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        supportActionBar?.setTitle("Books")

        val queue = Volley.newRequestQueue(this)
        val url = "http://13.235.250.119/v1/book/fetch_books/"

        val jsonObjectRequest =
            object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                println("Response is $it")

                val success = it.getBoolean("success")
                if (success) {
                    val data = it.getJSONArray("data")
                    for (i in 0 until data.length()) {
                        val bookJsonObject = data.getJSONObject(i)
                        val bookObject = Books(
                            bookJsonObject.getString("book_id"),
                            bookJsonObject.getString("name"),
                            bookJsonObject.getString("author"),
                            bookJsonObject.getString("rating"),
                            bookJsonObject.getString("price"),
                            bookJsonObject.getString("image")
                        )
                        bookList.add(bookObject)
                        layoutManager = LinearLayoutManager(this)
                        recyclerAdapter = Books_adapter(this, bookList)

                        recyclerBooks.adapter = recyclerAdapter
                        recyclerBooks.layoutManager = layoutManager
                    }
                }
            }, Response.ErrorListener {
                Toast.makeText(this, "Volley Error", Toast.LENGTH_SHORT).show()
            }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/json"
                    headers["token"] = "a22ec6b2ab5980"
                    return headers
                }
            }
        queue.add(jsonObjectRequest)

    }
}