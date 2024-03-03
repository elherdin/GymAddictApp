package com.example.gymaddict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)

        val photo: ImageView = findViewById(R.id.iv_image)
        val nameVariant: TextView = findViewById(R.id.text1)
        val nameMuscle: TextView = findViewById(R.id.text2)
        val nameDescription: TextView = findViewById(R.id.text3)
        val btnShareDetail: Button = findViewById(R.id.btn_share)

        photo.setImageResource(intent.getIntExtra("muscle_img", 0))
        nameMuscle.text = intent.getStringExtra("muscle_description")
        nameVariant.text = intent.getStringExtra("muscle_name")
        nameDescription.text = intent.getStringExtra("muscle_variant")

        btnShareDetail.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "Gym Addict"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "This content")
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

    }
}