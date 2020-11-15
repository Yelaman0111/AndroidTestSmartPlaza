package com.bignerdranch.android.smartplazatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        val ProductName = intent.getStringExtra("Name")
        val ProductPrice = intent.getIntExtra("Price", 0)
        val ProductPhoto = intent.getStringExtra("Photo")
        val ProductDesc = intent.getStringExtra("Desc")

        Log.i("PHOTO", ProductPhoto)


        val picasso: Picasso = Picasso.get()
        picasso.load("https://api.smartplaza.kz/mp/products/photos/" + ProductPhoto)
            .fit()
            .into(ivProductPhoto)


        tvName.text = ProductName
        tvPrice.text = ProductPrice.toString() + " тг"
        tvDescription.text = ProductDesc
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}