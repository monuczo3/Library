package com.monu.library3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setTitle("Home Page")
        var firebaseAuth = FirebaseAuth.getInstance()

        btn_logout.setOnClickListener{
            firebaseAuth.signOut()
            var intent= Intent(this, CreateNewAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        relativeLayout.setOnClickListener{
            var intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }
        relativeLayout2.setOnClickListener{
            var intent = Intent(this,BooksActivity::class.java)
            startActivity(intent)

        }
        relativeLayout3.setOnClickListener{
            var intent = Intent(this, AppsAcitivity::class.java)
            startActivity(intent)
        }
    }
}