package com.monu.library3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.monu.library3.R
import com.monu.library3.model.Movies
import com.squareup.picasso.Picasso

class Movies_adapter(val context: Context, val itemlist: ArrayList<Movies>) : RecyclerView.Adapter<Movies_adapter.MovieviewHolder>(){

    class MovieviewHolder(view: View) : RecyclerView.ViewHolder(view){
        val movieName : TextView = view.findViewById(R.id.movieName)
        val movieYear : TextView = view.findViewById(R.id.movieYear)
        val rlMovies : LinearLayout = view.findViewById(R.id.rlMovies)
        val movieRating : TextView = view.findViewById(R.id.rating)
        val moviePoster : ImageView = view.findViewById(R.id.moviePoster)
        val imgLike : ImageView = view.findViewById(R.id.img_like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_movies_single_row,parent,false)
        return MovieviewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieviewHolder, position: Int) {
        val Movies = itemlist[position]
        holder.movieName.text = Movies.movieTitle
        holder.movieYear.text = Movies.movieReleaseDate
        holder.movieRating.text = Movies.movieVoteAverage
        Picasso.get().load(Movies.moviePoster_path).into(holder.moviePoster)
        holder.rlMovies.setOnClickListener{
            Toast.makeText(context,"Clicked on ${holder.movieName.text}",Toast.LENGTH_SHORT).show()

        }
        var test : Boolean = true
        holder.imgLike.setOnClickListener{
            if(test == true){
                holder.imgLike.setImageResource(R.drawable.favourite2)
                test = false
            }
            else{
                holder.imgLike.setImageResource(R.drawable.favourite1)
                test = true
            }
        }

    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}