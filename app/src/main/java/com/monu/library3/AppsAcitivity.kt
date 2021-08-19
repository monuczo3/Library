package com.monu.library3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AppsAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apps_acitivity)
        supportActionBar?.setTitle("Apps")
    }
}