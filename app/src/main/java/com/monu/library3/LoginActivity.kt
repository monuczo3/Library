package com.monu.library3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        supportActionBar?.hide()

        btn_login.setOnClickListener {
            login()
        }

        txt_signup.setOnClickListener{
            val intent = Intent(this, CreateNewAccountActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(){
        val email : String  = etEmail.text.toString()
        val password: String  = et_password.text.toString()

        if(email == "" || password == ""){
            Toast.makeText(this,"Email and password can't be empty",Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Login Successful",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Login failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
}