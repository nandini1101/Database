package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class welcomeatvy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcomeatvy)

        val email=intent.getStringExtra(signin.KEY1)
        val name=intent.getStringExtra(signin.KEY2)
        val userid=intent.getStringExtra(signin.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tvwelcome)
        val mailText=findViewById<TextView>(R.id.tvmail)
        val idText=findViewById<TextView>(R.id.tvunique)



        welcomeText.text = "Welcome $name"
        mailText.text= "Mail : $email"
        idText.text="UserId : $userid"

    }
}