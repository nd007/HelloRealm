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
import com.mongodb.hellorealm.databinding.ActivityMainBinding
import com.mongodb.hellorealm.databinding.HomeActivityBinding
import com.mongodb.hellorealm.ui.home.HomeFragment

class HomeActivity : AppCompatActivity() {

//
private var homeActivityBinding:HomeActivityBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeActivityBinding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(homeActivityBinding!!.root)


    }



}