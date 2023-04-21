package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.database.signin.mail"
        const val KEY2 = "com.example.database.signin.name"
        const val KEY3 = "com.example.database.signin.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val signInButton=findViewById<Button>(R.id.btn)
        val username=findViewById<TextInputEditText>(R.id.username)

        signInButton.setOnClickListener{
            //take reference till node users in database
            //check the id whether it is present or not
            val uniqueid = username.text.toString()

            if(uniqueid.isNotEmpty()){
                //calling a function
                readData(uniqueid)
            }
            else{
                //giving message if username not there
                Toast.makeText(this,"please enter user name",Toast.LENGTH_SHORT).show()
            }

        }

    }
    //oncreate method over


        //keyword  function name  parameter:type of parameter
    private fun readData(uniqueid:String){

            databaseReference = FirebaseDatabase.getInstance().getReference("users")

            databaseReference.child(uniqueid).get().addOnSuccessListener {

                //if users exits or not
                if (it.exists()){
                    //welcome user in app,with intent and also pass
                    val email = it.child("email").value
                    val name = it.child("name").value
                    val userId =it.child(uniqueid).value

                    val intentwelcome = Intent(this,welcomeatvy::class.java)
                    intentwelcome.putExtra(KEY1,email.toString())
                    intentwelcome.putExtra(KEY2,name.toString())
                    intentwelcome.putExtra(KEY3,userId.toString())
                    startActivity(intentwelcome)
                }
                else{
                    Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener{
                Toast.makeText(this,"Failed,Error in Database",Toast.LENGTH_SHORT).show()
            }
    }
}