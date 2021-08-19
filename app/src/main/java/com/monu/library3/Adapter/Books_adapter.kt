package com.monu.library3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.monu.library3.R
import com.monu.library3.model.Books
import com.squareup.picasso.Picasso


class Books_adapter(val context: Context, val itemlist: ArrayList<Books>) : RecyclerView.Adapter<Books_adapter.BookviewHolder>(){

    class BookviewHolder(view: View) : RecyclerView.ViewHolder(view){
        val bookName : TextView = view.findViewById(R.id.bookName)
        val bookAuthor : TextView = view.findViewById(R.id.bookAuthor)
        val llBooks : LinearLayout = view.findViewById(R.id.llBooks)
        val bookRating : TextView = view.findViewById(R.id.bookRating)
        val bookImage : ImageView = view.findViewById(R.id.bookImage)
        val imgLike : ImageView = view.findViewById(R.id.img_like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_books_single_row,parent,false)
        return BookviewHolder(view)
    }

    override fun onBindViewHolder(holder: BookviewHolder, position: Int) {
        val Books = itemlist[position]
        holder.bookName.text = Books.bookName
        holder.bookAuthor.text = Books.bookAuthor
        holder.bookRating.text = Books.bookRating
        holder.llBooks.setOnClickListener{
            Toast.makeText(context,"Clicked on $(holder.bookName.text",Toast.LENGTH_SHORT).show()
        }
        Picasso.get().load(Books.bookImage).into(holder.bookImage)

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

