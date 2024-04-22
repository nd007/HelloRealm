package com.mongodb.hellorealm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mongodb.hellorealm.databinding.ActivityMainBinding
import com.mongodb.hellorealm.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fragmentCount = 0;


    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference


    private var uName = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topHeading.headerText.text = "LOGIN FORM"

        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("users")

        binding.buttonLogin.setOnClickListener {
            Log.e("##->12Username", binding.editTextUsername.text.toString())
            Log.e("##->12Pass", binding.editTextPassword.text.toString())
            if (binding.editTextUsername.text.toString()
                    .isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()
            ) {
                if (Utils.isValidPass(binding.editTextPassword.text.toString()) && binding.editTextUsername.text.length > 9) {
                    Log.e("##-1Username", binding.editTextUsername.text.toString())
                    Log.e("##-2Pass", binding.editTextPassword.text.toString())
                    validateUsernamePass(
                        binding.editTextUsername.text.toString(),
                        binding.editTextPassword.text.toString()
                    )

                } else {
                    Toast.makeText(
                        this,
                        "\"-Password must be at least 7 characters.\\n\" +\n" +
                                " \"-It must have at least 1 letter.\\n\" +\n" +
                                "  \"-It must have one special character.(like @,#,\\\$ etc.)\\n\" +\n" +
                                "   \"-It must have at least 1 digit.\",",
                        Toast.LENGTH_LONG
                    ).show()

                }
            } else {
                Toast.makeText(this, "Please enter both Username and Password ", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    private fun validateUsernamePass(userName: String, pass: String) {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and whenever data at this location is updated
                Log.d("## onDataChange->", "dataSnapshot: $dataSnapshot")
                for (userSnapshot in dataSnapshot.children) {
                    uName = userSnapshot.child("username").getValue(String::class.java).toString()
                    password =
                        userSnapshot.child("password").getValue(String::class.java).toString()

                    Log.d("## userNAme->", "Name: $uName")
                    Log.d("## userPAss->", " $password")
                }

                checkforLogin(uName, userName, password, pass)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("## error->", "Failed to read value.", error.toException())
            }
        })


/*
        if (userName.trim() =="Fininfocom" && pass.trim()=="Fin@123")
        {
            //navigateToFragment(HomeFragment())
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"Incorrect UserName and Password",Toast.LENGTH_LONG).show()
        }*/


    }

    private fun checkforLogin(uName: String, userName: String, password: String, pass: String) {

        if (userName.trim() == uName && pass.trim() == password) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Incorrect UserName and Password", Toast.LENGTH_LONG).show()
        }


    }


}