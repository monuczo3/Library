package com.monu.library3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_new_account.*

class CreateNewAccountActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_account)
        firebaseAuth = FirebaseAuth.getInstance()
        supportActionBar?.hide()

        var auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_sign_up.setOnClickListener {
            signupUser()
        }

        txt_login.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signupUser(){
        val email :String = etEmail.text.toString()
        val password : String = et_password.text.toString()
        val confirmPassword :String = et_confirm_password.text.toString()

        if(email == "" || password == "" || confirmPassword == ""){
            Toast.makeText(this,"Email and password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmPassword){
            Toast.makeText(this,"Password and confirm password does not match",Toast.LENGTH_SHORT).show()
            return
        }
        if(password.length <4){
            Toast.makeText(this,"Password too Short!",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) {
                    if(it.isSuccessful){
                        Toast.makeText(this,"User Created Successfully",Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Error Creating User",Toast.LENGTH_SHORT).show()
                    }
                }

    }
}