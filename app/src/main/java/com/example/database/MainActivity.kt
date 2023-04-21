package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupbutton=findViewById<Button>(R.id.button2)
        val etname=findViewById<TextInputEditText>(R.id.name)
        val etmail=findViewById<TextInputEditText>(R.id.mail)
        val userpassword=findViewById<TextInputEditText>(R.id.pw)
        val userid=findViewById<TextInputEditText>(R.id.user)

        signupbutton.setOnClickListener{

            val name = etname.text.toString()
            val mail=etmail.text.toString()
            val uniqueid=userid.text.toString()
            val password=userpassword.text.toString()


            val user=users(name,mail,password,uniqueid)
            database=FirebaseDatabase.getInstance().getReference("users")

            database.child(uniqueid).setValue(user).addOnSuccessListener {
                //etname.text?.clear()
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }

        val signintext=findViewById<TextView>(R.id.tvsignin)
        signintext.setOnClickListener{
            val opensigninatvy = Intent(this,signin::class.java)
            startActivity(opensigninatvy)
        }

    }
}